package avaj_launcher.utils.logger;

import java.io.IOException;
import java.util.Date;

public interface LogStrategy {
    public void write(CharSequence str) throws IOException;

    default void close() throws IOException {
    }

    default void flush() throws IOException {
    }

    default void write(String str) throws IOException {
        this.write((CharSequence) str);
    }

    default void write(StringBuffer str) throws IOException {
        write(str);
    }

    default void write(int n) throws IOException {
        write(Integer.toString(n));
    }

    default void write(long n) throws IOException {
        write(Long.toString(n));
    }

    default void write(double n) throws IOException {
        write(Double.toString(n));
    }

    default void write(Date date) throws IOException {
        write(date.toString());
    }
}
