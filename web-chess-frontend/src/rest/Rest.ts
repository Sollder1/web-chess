import EasyLogger from "../EasyLogger";
import LocalStorageHelper from "../LocalStorageHelper";

class Rest {

    public static BASE_URL_DEFAULT = "http://localhost:8101/";

    public static getBaseURL(): string {
        let url: string | null = LocalStorageHelper.getApiUrl();

        if (url == null) {
            return this.BASE_URL_DEFAULT;
        }
        return url;
    }

    public static async getMultiple<Type>(relativeUrl: string): Promise<Type[]> {
        return await this.get(relativeUrl) || [];
    }


    public static async getSpecific<Type>(relativeUrl: string): Promise<Type> {
        return await this.get(relativeUrl) || {} as Type;
    }


    private static async get<Type>(relativeUrl: string): Promise<Type | undefined> {
        const url = Rest.getBaseURL() + relativeUrl;
        const now = Date.now();

        try {
            let response: Response = await fetch(url, {method: 'GET', headers: Rest.getBasicHeaders()})
            EasyLogger.DEBUG(`GET Request against ${relativeUrl} took ${Date.now() - now} ms`)
            if (response.status !== 200) {
                await this.handleError(response);
            }
            return await response.json() as Type;
        } catch (e) {
            console.log("I failed you master!", e);
            return undefined;
        }
    }

    public static put<Type>(value: Type, relativeUrl: string): Promise<Type> {
        return this.save('PUT', value, relativeUrl);
    }

    public static post<Type>(value: Type | null, relativeUrl: string): Promise<Type> {
        return this.save('POST', value, relativeUrl);
    }

    private static async save<Type>(method: 'PUT' | 'POST', value: Type | null, relativeUrl: string): Promise<Type> {
        const url = Rest.getBaseURL() + relativeUrl;

        try {
            let response = await fetch(url, {
                method: method, body: JSON.stringify(value),
                headers: Rest.getBasicHeaders()
            });
            await Rest.handleSaveResponse(response)
            return await response.json() as Type;
        } catch (e) {
            console.log("I failed you master!", e);
            return {} as Type;
        }
    }

    private static async handleSaveResponse(response: Response) {
        if (response.status !== 200) {
            await this.handleError(response);
        } else {
            EasyLogger.INFO("Speichern erfolgreich!");
        }
    }

    public static delete(relativeUrl: string): Promise<any> {
        const url = Rest.getBaseURL() + relativeUrl;
        return fetch(url, {method: 'DELETE', headers: Rest.getBasicHeaders()})
            .then(response => Rest.handleDeleteResponse(response));
    }

    static handleDeleteResponse(response: Response): Response {
        if (response.status !== 200) {
            Rest.handleError(response);
        } else {
            EasyLogger.INFO("Löschen erfolgreich!");
        }
        return response;
    }

    private static async handleError(response: Response) {
        /*
        let errorBody: ErrorBody | undefined = await response.json() as ErrorBody;
        const error: string = errorBody.error || "Fataler Serverfehler";
        EasyLogger.ERROR(error);
        */
        throw new Error("Fehlercode: " + response.status);
    }

    private static getBasicHeaders() {
        return {
            "Selected-Language": "de",
            "Content-Type": "application/json"
        };
    }
}

export default Rest;