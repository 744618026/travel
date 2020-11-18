package travel.enums;

public enum ResultEnum {
    POI_NOT_EXISTS(1,"景点不存在"),
    FOLKWAYS_NOT_EXISTS(2,"民俗不存在"),
    COMMENT_NOT_EXISTS(3,"评论不存在"),
    IMAGE_NOT_EXISTS(4,"图片不存在"),
    HOTEL_NOT_EXISTS(5,"酒店不存在"),
    DATA_GET_NULL(6,"获取数据为空"),
    UPLOAD_FILE_NULL(7,"上传文件为空"),
    SQL_Syntax_Error_Exception(8,"SQL错误"),
    USER_NOT_EXISTS(9,"用户不存在"),
    USER_EXISTS(10,"用户已存在");
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
