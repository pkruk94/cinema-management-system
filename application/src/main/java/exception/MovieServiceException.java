package exception;

public class MovieServiceException extends RuntimeException {
    public MovieServiceException(String message) {
        super(message);
    }
}
