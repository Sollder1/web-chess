import React from 'react';
import './App.css';
import {BrowserRouter, Routes, Route} from "react-router-dom";
import StartPage from "./pages/start/StartPage";
import {AppBar} from "@mui/material";

interface Props {
}

interface State {
}

class App extends React.Component<Props, State> {

    constructor(pops: Props) {
        super(pops);
    }

    //https://reactrouter.com/docs/en/v6/getting-started/tutorial
    render() {
        return <>

            <AppBar position="relative" className="header" color="transparent">
                <h1 style={{textAlign: "center", fontSize: "40px"}}>Web-Chess</h1>
            </AppBar>

            <div style={{padding: "10px"}}>
                <BrowserRouter>
                    <Routes>
                        <Route path="/" element={<StartPage/>}/>
                        <Route path="/" element={<StartPage/>}/>

                    </Routes>
                </BrowserRouter>
            </div>


        </>
    };
}

export default App;
