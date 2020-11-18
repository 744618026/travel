package travel.controller.user;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import travel.dao.user.User;
import travel.dataForm.UserForm;
import travel.enums.ReturnMessageEnum;
import travel.enums.RoleEnum;
import travel.service.serviceImpl.user.UserServiceImpl;
import travel.vo.ResultVo;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @GetMapping("/check")
    public ResultVo check(@RequestParam("userName")String userName){
        ResultVo resultVo = new ResultVo();
        try{
            //用户不存在抛出异常
            userService.findByUserName(userName);
        }catch (Exception e){
            resultVo.setCode(ReturnMessageEnum.NOT_EXISTS.getCode());
            resultVo.setMessage(ReturnMessageEnum.NOT_EXISTS.getMessage());
        }
        return resultVo;
    }
    @PostMapping("/register")
    public ResultVo add(@Valid UserForm userForm, BindingResult bindingResult){
        ResultVo resultVo = new ResultVo();
        if(bindingResult.hasErrors()){
            resultVo.setCode(ReturnMessageEnum.FAILED.getCode());
            resultVo.setMessage(bindingResult.getFieldError().getDefaultMessage());
            return resultVo;
        }
        try {
            User user = new User();
            BeanUtils.copyProperties(userForm,user);
            user.setRole(RoleEnum.USER.getRole());
            user.setPassword(bCryptPasswordEncoder.encode(userForm.getPassword()));
            userService.insert(user);
            resultVo.setCode(ReturnMessageEnum.SUCCESS.getCode());
            resultVo.setMessage(ReturnMessageEnum.SUCCESS.getMessage());
        }catch (Exception e){
            resultVo.setCode(ReturnMessageEnum.FAILED.getCode());
            resultVo.setMessage(e.getMessage());
        }
        return resultVo;
    }
}
