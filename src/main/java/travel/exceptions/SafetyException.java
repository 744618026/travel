package travel.exceptions;

public class SafetyException extends SecurityException{
    private String message;

    public SafetyException(String message) {
        super(message);
        this.message = message;
    }
    @Override
    public String getMessage() {
        return message;
    }
}
