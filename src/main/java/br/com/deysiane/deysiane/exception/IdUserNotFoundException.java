package br.com.deysiane.deysiane.exception;

public class IdUserNotFoundException extends RuntimeException{
	
private static final long serialVersionUID = 1L;
	
	public IdUserNotFoundException() {
        super();
    }
    public IdUserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    public IdUserNotFoundException(String message) {
        super(message);
    }
    public IdUserNotFoundException(Throwable cause) {
        super(cause);
    }	

}
