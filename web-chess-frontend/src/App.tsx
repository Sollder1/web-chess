import React from 'react';
import './App.css';
import {BrowserRouter, Route, Switch} from "react-router-dom";
import StartPage from "./pages/start/StartPage";
import {AppBar} from "@mui/material";
import LobbyOverview from "./pages/lobby/LobbyOverview";
import Lobby from "./pages/lobby/Lobby";
import LocalStorageHelper from "./LocalStorageHelper";

interface Props {
}

interface State {
    playerName?: string
}

class App extends React.Component<Props, State> {

    constructor(pops: Props) {
        super(pops);
        this.state = {
            playerName: LocalStorageHelper.getPlayerName()
        }
        this.pollPlayerName();
    }

    //https://reactrouter.com/docs/en/v6/getting-started/tutorial
    //TODO: Headerbar fixen....
    render() {
        return <>

            <AppBar position="relative" className="header" color="transparent">
                <h1 style={{width: "100%", fontSize: "40px"}}><a href="/" style={{textAlign: "left"}}>Web-Chess</a>
                    <span style={{textAlign: "right"}}>{this.state.playerName}</span>
                </h1>
            </AppBar>

            <div style={{padding: "10px"}}>
                <BrowserRouter>
                    <Switch>
                        <Route exact path="/" component={StartPage}/>
                        <Route exact path="/lobbies" component={LobbyOverview}/>
                        <Route exact path="/lobbies/:id" component={Lobby}/>
                    </Switch>
                </BrowserRouter>
            </div>
        </>
    };

    private pollPlayerName() {
        this.setState({playerName: LocalStorageHelper.getPlayerName()})
        setTimeout(() => this.pollPlayerName(), 250);
    }

}

export default App;
