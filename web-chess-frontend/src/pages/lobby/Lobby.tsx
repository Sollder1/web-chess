import React from 'react';
import {RouteComponentProps} from "react-router-dom";
import LobbyApi from "../../rest/api/LobbyApi";
import LobbyPayload from "../../rest/model/LobbyPayload";
import {Button, Grid, List, ListItem, ListItemText, Paper} from "@mui/material";
import LocalStorageHelper from "../../LocalStorageHelper";
import FigureSetResolver from "./FigureSetResolver";
import CoordinatePayload from "../../rest/model/CoordinatePayload";
import PollPayload from "../../rest/model/PollPayload";
import LobbyToPlayerPayload from "../../rest/model/LobbyToPlayerPayload";


interface Props extends RouteComponentProps<{ id: string }> {
}

interface State {
    lobby?: LobbyPayload,
    selected?: CoordinatePayload,
    possibleMoves: CoordinatePayload[],
    me?: LobbyToPlayerPayload
}

class Lobby extends React.Component<Props, State> {

    public static EM_F = 0;

    public static PA_W = 2;
    public static PA_B = -2;
    public static KN_W = 7;
    public static KN_B = -7;
    public static BI_W = 6;
    public static BI_B = -6;
    public static CA_W = 10;
    public static CA_B = -10;
    public static QU_W = 18;
    public static QU_B = -18;
    public static KI_W = 127;
    public static KI_B = -127;


    constructor(pops: Props) {
        super(pops);
        this.state = {
            possibleMoves: []
        }
    }

    async componentDidMount() {
        const lobby = await LobbyApi.getLobby(this.props.match.params.id);

        let me = lobby.players?.filter(player => player.player.id === LocalStorageHelper.getPlayerId())[0];

        this.setState({lobby: lobby, me})
        //TODO: Error Handling...!
        this.poll();
    }

    render() {
        return <>

            <Grid container spacing={2} style={{marginTop: "20px"}}>

                <Grid item xl={4.5} lg={5.0} md={6} sm={5.5} xs={12}>
                    <Paper elevation={5} style={{padding: "20px"}}>
                        {this.renderGameField()}
                    </Paper>
                </Grid>
                <Grid item xl={0.5} lg={0.5} md={0.3} sm={0.5} xs={0}>
                </Grid>
                <Grid item xl={7} lg={6.5} md={5.7} sm={6} xs={12}>
                    <Paper elevation={5} style={{padding: "20px"}}>
                        <h2>Spieler [{this.state.lobby?.started + ""}]</h2>
                        <p>Dran: {this.state.me?.yourTurn + ""}</p>
                        <p>Farbe: {this.state.me?.playerColor}</p>

                        <List>
                            {this.state.lobby?.players?.map(value => {
                                return <ListItem key={value.player.id}>
                                    <ListItemText primary={<b>{value.player.userName}</b>}
                                                  secondary={value.connected ? "Verbunden" : "Getrennt"}/>
                                </ListItem>
                            })}
                        </List>

                        {this.state.lobby?.started ? null :
                            <Button variant="contained" onClick={() => this.startLobby()}>Spiel starten</Button>}

                    </Paper>
                </Grid>
            </Grid>


        </>
    };

    //KEINE Aktion an den den Server führt zu einem State Update, ALLES wird über das Polling gemacht.
    private startLobby() {
        LobbyApi.startLobby({
            lobbyId: this.state.lobby?.id,
            playerId: LocalStorageHelper.getPlayerId(),
            start: true
        });
    }


    private async poll() {
        const result: PollPayload = await LobbyApi.poll(this.state.lobby?.id, LocalStorageHelper.getPlayerId());

        if (result.newMoves && result.newMoves.length > 0) {
            const lobby = this.state.lobby;

            if (lobby) {
                result.newMoves.forEach(move => {
                    const figure = lobby.gameField[move.from.y][move.from.x];
                    lobby.gameField[move.from.y][move.from.x] = Lobby.EM_F;
                    lobby.gameField[move.to.y][move.to.x] = figure;
                    const audio = new Audio('/chess_move.wav');
                    audio.play();
                });
            }

            this.setState({lobby});
        }

        if (result.started) {
            const lobby = this.state.lobby;
            if (lobby) {
                lobby.started = true;
                this.setState({lobby})
            }
        }

        if (result.currentPlayerId) {
            let me = this.state.me;
            if (me) {
                me.yourTurn = result.currentPlayerId === LocalStorageHelper.getPlayerId();
                this.setState({me});
            }
        }

        if (result.playerJoined) {
            const lobby = this.state.lobby;
            if (lobby) {
                lobby.players?.push(result.playerJoined);
                this.setState({lobby});
            }
        }


        setTimeout(() => this.poll(), 250);
    }


    private renderGameField() {

        let counter = 1;

        return <>{
            this.state.lobby?.gameField.map(
                (array, y) => {

                    let v = <p style={{margin: 0, padding: 0, height: 50}}>
                        {array.map((value, x) => {
                            counter++;
                            let color = counter % 2 === 0 ? "white" : "black";

                            if (x === this.state.selected?.x && y === this.state.selected?.y) {
                                color = "blue";
                            }

                            if (this.markTile(x, y)) {
                                color = "green";
                            }

                            return <img style={{backgroundColor: color}} width={50} height={50}
                                        src={FigureSetResolver.getFigure("standard", value)} alt={value + ""}
                                        onClick={() => this.handleClick(x, y)}/>;
                        })}
                    </p>

                    counter++;
                    return v;

                }
            )
        }</>;
    }


    private markTile(x: number, y: number) {
        return this.state.possibleMoves.filter(value => value.x === x && value.y === y).length > 0;
    }

    private async handleClick(x: number, y: number) {

        const fieldValue: number = this.state.lobby?.gameField[y][x] || 0;
        const white = this.state.me?.playerColor === "WHITE";

        if (fieldValue === Lobby.EM_F || this.isMoveToEnemy(fieldValue, white) || this.isRochadeMove(fieldValue, white)) {
            if (this.state.selected) {
                this.setState({selected: undefined, possibleMoves: []})
                await LobbyApi.move(this.state.lobby?.id, LocalStorageHelper.getPlayerId(), {
                    from: this.state.selected,
                    to: {x, y}
                })
            }
        } else {
            await this.setState({selected: {x, y}});
            const moves = await LobbyApi.getPossibleMoves(this.state.lobby?.id,
                LocalStorageHelper.getPlayerId(), {x, y});
            this.setState({possibleMoves: moves});
        }

    }

    private isMoveToEnemy(fieldValue: number, white: boolean) {
        return (fieldValue < 0 && white) || (fieldValue > 0 && !white);
    }

    private isRochadeMove(fieldValue: number, white: boolean) {
        debugger;
        if (!this.state.selected) {
            return false;
        }
        const selectedCode: number = this.state.lobby?.gameField[this.state.selected.y][this.state.selected.x] || 0;
        if (selectedCode === Lobby.KI_B || selectedCode === Lobby.KI_W) {
            return (fieldValue === Lobby.CA_W && white) || (fieldValue === Lobby.CA_B && !white);
        }
        return false;
    }
}

export default Lobby;
