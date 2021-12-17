package de.sollder1.webchess.backend.util.logging;

import java.io.IOException;
import java.util.logging.*;

public class LoggerFactory {

    private static final Level CONSOLE_LEVEL = Level.ALL;
    private static final Level FILE_LEVEL = Level.ALL;

    public static Logger get(Class<?> clazz) {

        try {
            Logger logger = Logger.getLogger(clazz.getName());
            logger.setLevel(Level.ALL);

            Handler consoleHandler = new ConsoleHandler();
            consoleHandler.setLevel(CONSOLE_LEVEL);
            logger.addHandler(consoleHandler);

            Handler fileHandler = new FileHandler("web-chess.log");
            fileHandler.setLevel(FILE_LEVEL);
            logger.addHandler(fileHandler);

            return logger;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
