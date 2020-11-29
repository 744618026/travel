package travel.vo;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultVo<T> implements Serializable {
    private static final long serialVersionUID = 1161647445596221995L;
    private Integer code;
    private String message;
    private T data;
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
    public String toString(){
        return "{\n" +
                "\"code\":" + this.code+",\n"+
                "\"message\":" + this.message+
                "}";
    }
}
