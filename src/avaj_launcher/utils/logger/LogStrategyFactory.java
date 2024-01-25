package avaj_launcher.utils.logger;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

public class LogStrategyFactory {
    public static LogStrategy getFileLogStrategy(String filePath) throws IOException {
        return new FileLogStrategy(filePath);
    }

    public static LogStrategy getOutputStreamLogStrategy(OutputStream stream) throws IOException {
        return new OutputStreamLogStrategy(stream);
    }

    public static LogStrategy getPrintStreamLogStrategy(PrintStream stream) throws IOException {
        return new PrintStreamStrategy(stream);
    }
}
