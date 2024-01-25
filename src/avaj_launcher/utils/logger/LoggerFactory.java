package avaj_launcher.utils.logger;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

public class LoggerFactory {
    public static Logger initOutputStreamLogger(OutputStream stream) {
        return Logger.initailize(new OutputStreamLogStrategy(stream));
    }

    public static Logger initFileLogger(String path) throws IOException {
        return Logger.initailize(new FileLogStrategy(path));
    }

    public static Logger initPrintStreamLogger(PrintStream stream) {
        return Logger.initailize(new PrintStreamStrategy(stream));
    }
}
