package travel.utils;

import travel.enums.ReturnMessageEnum;
import travel.vo.ResultVo;

import java.io.Serializable;

public class ResultUtil {
    //private static final long serialVersionUID = -232961394107847165L;
    public static ResultVo success(Object object){
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(ReturnMessageEnum.SUCCESS.getCode());
        resultVo.setMessage(ReturnMessageEnum.SUCCESS.getMessage());
        resultVo.setData(object);
        return resultVo;
    }
    public static ResultVo success(){
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(ReturnMessageEnum.SUCCESS.getCode());
        resultVo.setMessage(ReturnMessageEnum.SUCCESS.getMessage());
        return resultVo;
    }
    public static ResultVo fail(){
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(ReturnMessageEnum.FAILED.getCode());
        resultVo.setMessage(ReturnMessageEnum.FAILED.getMessage());
        return resultVo;
    }
    public static ResultVo fail(String message){
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(ReturnMessageEnum.FAILED.getCode());
        resultVo.setMessage(message);
        return resultVo;
    }
}
