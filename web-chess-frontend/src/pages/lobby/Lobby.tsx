import React from 'react';
import {RouteComponentProps} from "react-router-dom";
import LobbyApi from "../../rest/api/LobbyApi";
import LobbyPayload from "../../rest/model/LobbyPayload";
import {Button, Grid, List, ListItem, ListItemText, Paper} from "@mui/material";
import LocalStorageHelper from "../../LocalStorageHelper";


interface Props extends RouteComponentProps<{ id: string }> {
}

interface State {
    lobby?: LobbyPayload
}

class Lobby extends React.Component<Props, State> {


    constructor(pops: Props) {
        super(pops);
        this.state = {}
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
                        Spiel nicht gestartet
                    </Paper>
                </Grid>
                <Grid item xl={0.5} lg={0.5} md={0.3} sm={0.5} xs={0}>
                </Grid>
                <Grid item xl={7} lg={6.5} md={5.7} sm={6} xs={12}>
                    <Paper elevation={5} style={{padding: "20px"}}>
                        <h2>Spieler</h2>

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
        const result = await LobbyApi.poll(this.state.lobby?.id, LocalStorageHelper.getPlayerId());
        console.log(result);
        setTimeout(() => this.poll(), 250);
    }


}

export default Lobby;
