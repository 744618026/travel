package travel.utils;

import travel.enums.ResultEnum;
import travel.enums.ReturnMessage;
import travel.vo.ResultVo;

public class ResultUtil {
    public static ResultVo success(Object object){
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(ReturnMessage.SUCCESS.getCode());
        resultVo.setMessage(ReturnMessage.SUCCESS.getMessage());
        resultVo.setData(object);
        return resultVo;
    }
    public static ResultVo fail(String message){
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(ReturnMessage.FAILED.getCode());
        resultVo.setMessage(message);
        return resultVo;
    }
}
