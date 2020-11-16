package travel.exceptions;

import org.springframework.web.bind.annotation.ExceptionHandler;
import travel.enums.ResultEnum;
import travel.vo.ResultVo;

import java.sql.SQLSyntaxErrorException;

public abstract class ExceptionHandlers {
    @ExceptionHandler(SQLSyntaxErrorException.class)
    public String handleSQLSyntaxErrorException(Exception e){
        e.printStackTrace();
        return ResultEnum.SQL_Syntax_Error_Exception.getMessage();
    }
}
