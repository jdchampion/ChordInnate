package chordinnate.exception;

public class ChordInnateException extends RuntimeException {
    public ChordInnateException() {
        super();
    }

    public ChordInnateException(String message) {
        super(message);
    }

    public ChordInnateException(String message, Throwable cause) {
        super(message, cause);
    }

    public ChordInnateException(Throwable cause) {
        super(cause);
    }

    protected ChordInnateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
