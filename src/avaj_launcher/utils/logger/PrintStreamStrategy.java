package avaj_launcher.utils.logger;

import java.io.IOException;
import java.io.PrintStream;

/**
 * @note PrintStream 의 경우 close를 하더라도 Stream이 실제로 닫히지 않습니다.
 */
public class PrintStreamStrategy implements LogStrategy {
    private PrintStream writer;

    public PrintStreamStrategy(PrintStream stream) {
        writer = stream;
    }

    @Override
    public void write(CharSequence str) throws IOException {
        writer.print(str);
    }

    @Override
    public void flush() throws IOException {
        writer.flush();
    }

    @Override
    public void close() throws IOException {
        writer.flush();
    }
}
