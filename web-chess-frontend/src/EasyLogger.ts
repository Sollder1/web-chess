class EasyLogger {

    /*
        3 = DEBUG
        2 = INFO
        1 = WARN
        0 = ERROR
        Default production level would be 1
     */
    private static LEVEL = 1;


    public static DEBUG(message?: string) {
        if (this.LEVEL >= 3) {
            console.log(`[DEBUG] - (${Date.now()}) - "${message}"`);
        }
    }

    public static INFO(message?: string) {
        if (this.LEVEL >= 2) {
            console.log(`[INFO] - (${Date.now()}) - "${message}"`);
        }
    }

    public static WARN(message?: string) {
        if (this.LEVEL >= 1) {
            console.log(`[WARN] - (${Date.now()}) - "${message}"`);
        }
    }

    public static ERROR(message?: string) {
        if (this.LEVEL >= 0) {
            console.log(`[ERROR] - (${Date.now()}) - "${message}"`);
        }
    }

}

export default EasyLogger;