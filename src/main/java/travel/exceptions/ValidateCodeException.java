package travel.exceptions;

import javax.validation.executable.ValidateOnExecution;
import javax.xml.bind.ValidationException;

public class ValidateCodeException extends ValidationException {
    public ValidateCodeException(String message) {
        super(message);
    }
}
