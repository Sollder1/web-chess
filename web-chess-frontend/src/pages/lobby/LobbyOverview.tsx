import React from 'react';
import {
    Button,
    Dialog,
    DialogActions,
    DialogContent,
    DialogTitle,
    List,
    ListItem,
    ListItemText,
    Paper,
    TextField
} from "@mui/material";
import LobbyApi from "../../rest/api/LobbyApi";
import LobbyPayload from "../../rest/model/LobbyPayload";
import {Redirect} from "react-router";
import Draggable from 'react-draggable';
import LocalStorageHelper from "../../LocalStorageHelper";

interface Props {
}

interface State {
    lobbies: LobbyPayload[],
    dialogOpen: boolean,
    lobbyModalName: string,
    listHeight: number,
    redirect: boolean
}

class LobbyOverview extends React.Component<Props, State> {

    private redirectId?: string;

    constructor(pops: Props) {
        super(pops);
        this.state = {
            lobbies: [],
            dialogOpen: false,
            listHeight: window.outerHeight / 2,
            redirect: false,
            lobbyModalName: ""
        }
    }

    componentDidMount() {
        window.onresize = ev => this.setState({listHeight: window.outerHeight / 2});
        this.handleSearch("");
    }

    render() {
        return <Paper elevation={5} style={{padding: "10px"}}>
            {
                this.state.redirect ? <Redirect push to={`/lobbies/${this.redirectId}`}/> : null
            }

            <Dialog
                PaperComponent={LobbyOverview.PaperComponent}
                open={this.state.dialogOpen}
                onClose={() => this.setState({dialogOpen: false})}
                aria-labelledby="draggable-dialog-title"
            >
                <DialogTitle style={{cursor: 'move'}} id="draggable-dialog-title">
                    Lobby erstellen
                </DialogTitle>
                <DialogContent style={{padding: "10px"}}>
                    <TextField value={this.state.lobbyModalName}
                               onChange={ev => this.setState({lobbyModalName: ev.target.value})} label="Name"
                               style={{width: "500px"}} autoFocus/>

                </DialogContent>

                <DialogActions>
                    <Button variant="contained" style={{marginTop: "10px"}} size="large"
                            onClick={ev => this.addLobby()}>Hinzufügen</Button>
                </DialogActions>

            </Dialog>


            <div style={{margin: "10px 10px 10px 0px"}}>
                <Button style={{marginRight: "10px"}} variant="contained"
                        onClick={() => this.setState({dialogOpen: true})}>Lobby erstellen</Button>
                <Button disabled variant="contained">Gegen KI</Button>
            </div>

            <TextField label="Suchen" style={{width: "100%"}} onChange={ev => this.handleSearch(ev.target.value)}/>

            <List
                sx={{
                    position: 'relative',
                    overflow: 'auto',
                    maxHeight: this.state.listHeight,
                    padding: "10px"
                }}
            >
                {this.state.lobbies.map(value => {
                    return <ListItem key={value.id} onClick={() => this.joinLobby(value.id)} className="hoverable">
                        <ListItemText primary={value.name} secondary={"Spieler: " + value.players?.length + "/2"}/>
                    </ListItem>
                })}
            </List>
        </Paper>
    };

    private static PaperComponent(props: any) {
        return (
            <Draggable
                handle="#draggable-dialog-title"
                cancel={'[class*="MuiDialogContent-root"]'}
            >
                <Paper {...props} />
            </Draggable>
        );
    }

    private async handleSearch(query: string) {
        let lobbies = await LobbyApi.queryLobbies(query);
        this.setState({lobbies: lobbies});
    }

    private async addLobby() {
        const payload: LobbyPayload = {name: this.state.lobbyModalName,
            players: [{player: {id: LocalStorageHelper.getPlayerId()}}]};

        const lobby = await LobbyApi.addLobby(payload);
        this.redirectId = lobby.id;
        this.setState({redirect: true});
    }

    private async joinLobby(lobbyId?: string) {
        //TODO: error Handling...
        await LobbyApi.joinLobby(lobbyId, LocalStorageHelper.getPlayerId());
        this.redirectId = lobbyId;
        this.setState({redirect: true});
    }

}

export default LobbyOverview;
