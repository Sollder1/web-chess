import React from 'react';
import {Button, TextField} from "@mui/material";
import PlayerApi from "../../rest/api/PlayerApi";
import {Redirect} from "react-router-dom";
import LocalStorageHelper from "../../LocalStorageHelper";

interface Props {
}

interface State {
    userId?: string,
    username: string,
    redirect: boolean
}

class StartPage extends React.Component<Props, State> {


    constructor(pops: Props) {
        super(pops);
        this.state = {
            redirect: false,
            username: ""
        }
    }

    async componentDidMount() {
        let userId = LocalStorageHelper.getPlayerId();
        if (userId) {
            let player = await PlayerApi.getUser(userId);
            if (player.id) {
                LocalStorageHelper.setPlayerName(player.userName);
                this.setState({redirect: true});
            }
        }
    }

    render() {
        return <>
            {
                this.state.redirect ? <Redirect
                    to={{
                        pathname: "/lobbies"
                    }}
                /> : null
            }
            <TextField label="Nutzername" value={this.state.username}
                       onChange={event => this.setState({username: event.target.value})}/>
            <Button variant="contained" size="large"
                    style={{height: "39.5px"}} onClick={event => this.login()}>Los gehts!</Button>
        </>
    };


    private async login() {
        let player = await PlayerApi.createUser({userName: this.state.username});
        LocalStorageHelper.setPlayerId(player.id);
        LocalStorageHelper.setPlayerName(player.userName);

        if(player.id) {
            this.setState({redirect: true});
        }else {
            //TODO: error Managment...
        }
    }

}

export default StartPage;
