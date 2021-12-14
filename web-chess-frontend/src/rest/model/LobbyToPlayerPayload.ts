import PlayerPayload from "./PlayerPayload";

export default interface LobbyToPlayerPayload {
    connected?: boolean,
    player: PlayerPayload
}