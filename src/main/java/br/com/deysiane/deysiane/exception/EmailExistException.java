package br.com.deysiane.deysiane.exception;

public class EmailExistException extends RuntimeException{
	
private static final long serialVersionUID = 1L;
	
	public EmailExistException() {
        super();
    }
    public EmailExistException(String message, Throwable cause) {
        super(message, cause);
    }
    public EmailExistException(String message) {
        super(message);
    }
    public EmailExistException(Throwable cause) {
        super(cause);
    }

}
