export default class LocalStorageHelper {

    public static getPlayerId(): string | undefined {
        return localStorage.getItem("playerId") || undefined;
    }

    public static setPlayerId(value?: string) {
        return localStorage.setItem("playerId", value || "");
    }

    public static getPlayerName(): string | undefined {
        return localStorage.getItem("playerName") || undefined;
    }

    public static setPlayerName(value?: string) {
        return localStorage.setItem("playerName", value || "");
    }

    public static getApiUrl(): string | null {
        return localStorage.getItem("api-url");
    }
}