package travel.exceptions;

public class NullException extends NullPointerException{
    private String message;
    public NullException(String message){
        super(message);
        this.message = message;
    }
    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
