package travel.enums;

public enum ResultEnum {
    POI_NOT_EXISTS(1,"景点不存在"),
    FOLKWAYS_NOT_EXISTS(2,"民俗不存在");
    private Integer code;
    private String message;
    ResultEnum(Integer code,String message){
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
