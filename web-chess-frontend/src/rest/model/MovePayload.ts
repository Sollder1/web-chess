import CoordinatePayload from "./CoordinatePayload";

export default interface MovePayload {
    from: CoordinatePayload,
    to: CoordinatePayload
}