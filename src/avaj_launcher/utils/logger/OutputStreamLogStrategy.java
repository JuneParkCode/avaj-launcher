package avaj_launcher.utils.logger;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class OutputStreamLogStrategy implements LogStrategy {
    private OutputStreamWriter writer;

    public OutputStreamLogStrategy(OutputStream stream) {
        writer = new OutputStreamWriter(stream);
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
