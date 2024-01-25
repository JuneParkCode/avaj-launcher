package avaj_launcher.utils.logger;

import java.io.FileWriter;
import java.io.IOException;

public class FileLogStrategy implements LogStrategy {
    private FileWriter writer;

    public FileLogStrategy(String filePath) throws IOException {
        writer = new FileWriter(filePath, true);
    }

    @Override
    public void write(CharSequence str) throws IOException {
        writer.append(str);
    }

    @Override
    public void close() throws IOException {
        writer.close();
    }

    @Override
    public void flush() throws IOException {
        writer.flush();
    }
}
