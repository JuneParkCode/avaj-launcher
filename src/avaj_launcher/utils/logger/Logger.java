package avaj_launcher.utils.logger;

import java.io.IOException;

public class Logger {
    private static Logger instance = null;
    private static LogStrategy logStrategy = null;
    private static LogStrategy errStrategy = null;
    private static LogStrategy debugStrategy = null;
    private static LogStrategy warnStrategy = null;

    private Logger(LogStrategy logLogStrategy, LogStrategy errLogStrategy, LogStrategy debuLogStrategy,
            LogStrategy warnLogStrategy) {
        logStrategy = logLogStrategy;
        errStrategy = errLogStrategy;
        debugStrategy = debuLogStrategy;
        warnStrategy = warnLogStrategy;
    }

    public static Logger initailize(LogStrategy strategy) {
        if (instance == null) {
            synchronized (Logger.class) {
                instance = new Logger(strategy, strategy, strategy, strategy);
            }
        }
        return instance;
    }

    public static Logger initailize(LogStrategy logLogStrategy, LogStrategy errLogStrategy, LogStrategy debuLogStrategy,
            LogStrategy warnLogStrategy) {
        if (instance == null) {
            synchronized (Logger.class) {
                instance = new Logger(logLogStrategy, errLogStrategy, debuLogStrategy, warnLogStrategy);
            }
        }
        return instance;
    }

    /** this logger ignores Exception */
    public static void log(CharSequence content) {
        if (logStrategy == null || instance == null) {
            System.err.println("Logger: not initialized");
            return;
        }
        try {
            logStrategy.write(content);
            logStrategy.write("\n");
        } catch (IOException e) {
            System.err.println(String.format("Logger: log failed\n%s", e.getMessage()));
        }
    }

    public static void debug(CharSequence content) {
        if (debugStrategy == null || instance == null) {
            System.err.println("Logger: not initialized");
            return;
        }
        try {
            debugStrategy.write(content);
            debugStrategy.write("\n");
        } catch (IOException e) {
            System.err.println(String.format("Logger: log failed\n%s", e.getMessage()));
        }
    }

    public static void err(CharSequence content) {
        if (errStrategy == null || instance == null) {
            System.err.println("Logger: not initialized");
            return;
        }
        try {
            errStrategy.write(content);
            errStrategy.write("\n");
        } catch (IOException e) {
            System.err.println(String.format("Logger: log failed\n%s", e.getMessage()));
        }
    }

    public static void warn(CharSequence content) {
        if (warnStrategy == null || instance == null) {
            System.err.println("Logger: not initialized");
            return;
        }
        try {
            warnStrategy.write(content);
            warnStrategy.write("\n");
        } catch (IOException e) {
            System.err.println(String.format("Logger: log failed\n%s", e.getMessage()));
        }
    }

    public static void close() {
        if (instance == null) {
            System.err.println("Logger: not initialized");
            return;
        }
        closeLogStrategy();
        closeErrLogStrategy();
        closeDebugLogStrategy();
        closeWarnLogStrategy();
    }

    public static void flush() {
        if (instance == null) {
            System.err.println("Logger: not initialized");
            return;
        }
        flushLogStrategy();
        flushErrLogStrategy();
        flushDebugLogStrategy();
        flushWarnLogStrategy();
    }

    private static void closeLogStrategy() {
        if (logStrategy == null) {
            System.err.println("Logger: not initialized Log Strategy");
            return;
        }
        try {
            logStrategy.close();
        } catch (IOException e) {
            System.err.println("Logger: close failed");
            for (Throwable exception : e.getSuppressed()) {
                System.err.println(exception.getMessage());
            }
        }
    }

    private static void closeErrLogStrategy() {
        if (errStrategy == null) {
            System.err.println("Logger: not initialized Err Strategy");
            return;
        }
        try {
            errStrategy.close();
        } catch (IOException e) {
            System.err.println("Logger: close failed");
            for (Throwable exception : e.getSuppressed()) {
                System.err.println(exception.getMessage());
            }
        }
    }

    private static void closeDebugLogStrategy() {
        if (debugStrategy == null) {
            System.err.println("Logger: not initialized Debug Strategy");
            return;
        }
        try {
            debugStrategy.close();
        } catch (IOException e) {
            System.err.println("Logger: close failed");
            for (Throwable exception : e.getSuppressed()) {
                System.err.println(exception.getMessage());
            }
        }
    }

    private static void closeWarnLogStrategy() {
        if (warnStrategy == null) {
            System.err.println("Logger: not initialized Warn Strategy");
            return;
        }
        try {
            warnStrategy.close();
        } catch (IOException e) {
            System.err.println("Logger: close failed");
            for (Throwable exception : e.getSuppressed()) {
                System.err.println(exception.getMessage());
            }
        }
    }

    private static void flushLogStrategy() {
        if (logStrategy == null) {
            System.err.println("Logger: not initialized Log Strategy");
            return;
        }
        try {
            logStrategy.flush();
        } catch (IOException e) {
            System.err.println("Logger: flush failed");
            for (Throwable exception : e.getSuppressed()) {
                System.err.println(exception.getMessage());
            }
        }
    }

    private static void flushErrLogStrategy() {
        if (errStrategy == null) {
            System.err.println("Logger: not initialized Err Strategy");
            return;
        }
        try {
            errStrategy.flush();
        } catch (IOException e) {
            System.err.println("Logger: flush failed");
            for (Throwable exception : e.getSuppressed()) {
                System.err.println(exception.getMessage());
            }
        }
    }

    private static void flushDebugLogStrategy() {
        if (debugStrategy == null) {
            System.err.println("Logger: not initialized Debug Strategy");
            return;
        }
        try {
            debugStrategy.flush();
        } catch (IOException e) {
            System.err.println("Logger: flush failed");
            for (Throwable exception : e.getSuppressed()) {
                System.err.println(exception.getMessage());
            }
        }
    }

    private static void flushWarnLogStrategy() {
        if (warnStrategy == null) {
            System.err.println("Logger: not initialized Warn Strategy");
            return;
        }
        try {
            warnStrategy.flush();
        } catch (IOException e) {
            System.err.println("Logger: flush failed");
            for (Throwable exception : e.getSuppressed()) {
                System.err.println(exception.getMessage());
            }
        }
    }
}
