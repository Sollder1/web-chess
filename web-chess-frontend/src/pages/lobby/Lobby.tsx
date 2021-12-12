import React from 'react';
import {RouteComponentProps} from "react-router-dom";
import LobbyApi from "../../rest/api/LobbyApi";
import LobbyPayload from "../../rest/model/LobbyPayload";


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

    }

    render() {
        return <>
            <p>State:</p>
            <code >
                {JSON.stringify(this.state, null, 2)}
            </code>
        </>
    };


}

export default Lobby;
