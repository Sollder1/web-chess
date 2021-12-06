import React from 'react';

interface Props {
}

interface State {
}

class StartPage extends React.Component<Props, State> {

    constructor(pops: Props) {
        super(pops);
    }

    render() {
        return <>
           <h1>Stell dir mal vor, dass wäre Schach...!</h1>
        </>
    };
}

export default StartPage;
