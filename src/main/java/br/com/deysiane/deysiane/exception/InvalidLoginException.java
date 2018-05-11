package br.com.deysiane.deysiane.exception;

public class InvalidLoginException extends RuntimeException {
	
private static final long serialVersionUID = 1L;
	
	public InvalidLoginException() {
        super();
    }
    public InvalidLoginException(String message, Throwable cause) {
        super(message, cause);
    }
    public InvalidLoginException(String message) {
        super(message);
    }
    public InvalidLoginException(Throwable cause) {
        super(cause);
    }


}
