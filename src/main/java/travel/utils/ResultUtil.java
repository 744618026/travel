package travel.utils;

import travel.enums.ReturnMessageEnum;
import travel.vo.ResultVo;

public class ResultUtil {
    public static ResultVo success(Object object){
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(ReturnMessageEnum.SUCCESS.getCode());
        resultVo.setMessage(ReturnMessageEnum.SUCCESS.getMessage());
        resultVo.setData(object);
        return resultVo;
    }
    public static ResultVo fail(String message){
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(ReturnMessageEnum.FAILED.getCode());
        resultVo.setMessage(message);
        return resultVo;
    }
}
