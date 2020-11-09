package travel.exceptions;

public class SellException extends RuntimeException{
    private String message;
    public SellException(String message){
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
