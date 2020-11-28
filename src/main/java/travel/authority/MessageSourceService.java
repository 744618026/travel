package travel.authority;

import org.springframework.context.MessageSource;

import java.util.Locale;

public class MessageSourceService {
    private final MessageSource messageSource;
    public MessageSourceService(MessageSource messageSource) {
        this.messageSource = messageSource;
    }
    public String getMessage(String msgKey, Object[] args) {
        return messageSource.getMessage(msgKey, args, Locale.CHINA);
    }

    public String getMessage(String msgKey) {
        return messageSource.getMessage(msgKey, null,Locale.CHINA);
    }
}
