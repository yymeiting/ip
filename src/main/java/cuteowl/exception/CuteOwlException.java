package cuteowl.exception;

public class CuteOwlException extends Exception {
    public CuteOwlException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return getMessage();
    }
}
