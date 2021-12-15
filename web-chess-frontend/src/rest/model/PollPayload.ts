export default interface PollPayload {

    lobbyId: string,
    playerId: string,


    //Alle diese Payloads können auch leer (undefined) sein, wenn es dort keine Änderugnen auf dem Server gab.

    started?: boolean,
    //Schachmatt: undefined wenn unbekannt, ansonsten 1 = weiß, 2 = Schwarz, 3 = unentschieden
    checkmate?: number,
    //Matt: undefined wenn unbekannt, ansonsten 1 = weiß, 2 = Schwarz
    check: number,

    //Initiales Spielfeld ist immer gleich, übertragen werden nur die einzelnen Züge (mehrere z.B. bei Rochade)
    newMoves?: [{
        fromX: number,
        toX: number,
        fromY: number,
        toY: number
    }],


    //##### HINT: optional, vorerst nicht nötig #####

    //Gibt die übrige Zeit auf der Schachuhr an, wenn aktiviert
    //chessClockTimeMs?: number

}