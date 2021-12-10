import React from 'react';
import {Button, Input, TextField} from "@mui/material";
import {Navigate} from 'react-router'

interface Props {
}

interface State {
}

class Lobby extends React.Component<Props, State> {


    constructor(pops: Props) {
        super(pops);
    }

    render() {
        return <>
            <h1>Lobby</h1>
        </>
    };



}

export default Lobby;
