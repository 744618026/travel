package travel.enums;

public enum ReturnMessageEnum {
    SUCCESS(0,"成功"),
    FAILED(1,"失败");

    private Integer code;
    private String message;
    ReturnMessageEnum(Integer code, String message){
        this.code = code;
        this.message = message;
    }
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
