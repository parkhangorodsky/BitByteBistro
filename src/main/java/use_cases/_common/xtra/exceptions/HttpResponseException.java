package use_cases._common.xtra.exceptions;

public class HttpResponseException extends RuntimeException {
    public HttpResponseException(String message) {
            super(message);
        }
}
