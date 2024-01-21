package avaj_launcher.simulator;

import java.io.FileWriter;
import java.io.IOException;

public class Logger {
    private static Logger instance;
    private static FileWriter fileWriter;

    private Logger(String filePath) {
        try (FileWriter fw = new FileWriter(filePath)) {
            fileWriter = fw;
        } catch (IOException e) {
            System.err.println("Logger file is invaild");
            System.exit(1);
        }
    }

    public static Logger initailize(String filePath) {
        if (instance == null) {
            synchronized (Logger.class) {
                instance = new Logger(filePath);
            }
        }
        return instance;
    }

    /** this logger ignores Exception */
    public static void log(CharSequence content) {
        try {
            fileWriter.append(content + "\n");
        } catch (IOException e) {
            System.err.println("Logger file is invaild");
        }
    }

    public static void close() {
        if (instance == null || fileWriter == null)
            return;
        try {
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            System.err.println("Failed to close log file");
            System.exit(1);
        }
    }
}
