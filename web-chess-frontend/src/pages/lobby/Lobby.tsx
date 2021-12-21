import React from 'react';
import {RouteComponentProps} from "react-router-dom";
import LobbyApi from "../../rest/api/LobbyApi";
import LobbyPayload from "../../rest/model/LobbyPayload";
import {Button, Grid, List, ListItem, ListItemText, Paper} from "@mui/material";
import LocalStorageHelper from "../../LocalStorageHelper";
import FigureSetResolver from "./FigureSetResolver";
import CoordinatePayload from "../../rest/model/CoordinatePayload";
import MovePayload from "../../rest/model/MovePayload";
import PollPayload from "../../rest/model/PollPayload";


interface Props extends RouteComponentProps<{ id: string }> {
}

interface State {
    lobby?: LobbyPayload,
    selected?: CoordinatePayload,
    possibleMoves: CoordinatePayload[],
    myTurn: boolean
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
            possibleMoves: [],
            myTurn: true
        }
    }

    async componentDidMount() {
        const lobby = await LobbyApi.getLobby(this.props.match.params.id);
        this.setState({lobby: lobby})
        //TODO: Error Handling...!
        this.poll();
    }

    render() {
        return <>
            <p>State:</p>
            <code>
                {JSON.stringify(this.state, null, 2)}
            </code>

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
        console.log("Polling...")
        const result: PollPayload = await LobbyApi.poll(this.state.lobby?.id, LocalStorageHelper.getPlayerId());

        if (result.newMoves && result.newMoves.length > 0) {
            const lobby = this.state.lobby;

            if(lobby) {
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
            this.setState({myTurn: result.currentPlayerId === LocalStorageHelper.getPlayerId()});
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
        if (this.state.lobby?.gameField[y][x] !== Lobby.EM_F) {

            await this.setState({selected: {x, y}});
            const moves = await LobbyApi.getPossibleMoves(this.state.lobby?.id,
                LocalStorageHelper.getPlayerId(), {x, y});
            this.setState({possibleMoves: moves});
        } else {
            if (this.state.selected) {
                this.setState({selected: undefined, possibleMoves: []})
                await LobbyApi.move(this.state.lobby?.id, LocalStorageHelper.getPlayerId(), {
                    from: this.state.selected,
                    to: {x, y}
                })
            }
        }

    }
}

export default Lobby;
