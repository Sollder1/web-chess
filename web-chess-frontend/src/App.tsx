import React from 'react';
import './App.css';
import {BrowserRouter, Routes, Route} from "react-router-dom";
import StartPage from "./pages/start/StartPage";

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
            <BrowserRouter>
                <Routes>
                    <Route path="/" element={<StartPage/>}/>
                </Routes>
            </BrowserRouter>
        </>
    };
}

export default App;
