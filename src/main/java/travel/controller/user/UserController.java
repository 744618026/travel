package travel.controller.user;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import travel.dao.user.User;
import travel.dataForm.UserForm;
import travel.enums.ResultEnum;
import travel.enums.ReturnMessageEnum;
import travel.enums.RoleEnum;
import travel.service.serviceImpl.user.UserServiceImpl;
import travel.utils.ResultUtil;
import travel.vo.ResultVo;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.PrintWriter;

@RestController
@RequestMapping("")
public class UserController {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @GetMapping("/checkUser")
    //检查用户是否存在
    public ResultVo check(@RequestParam("userName")String userName) {
        try{
            //用户不存在抛出异常
            userService.findByUserName(userName);
            return ResultUtil.success();
        }catch (Exception e){
            return ResultUtil.fail(e.getMessage());
        }
    }
    @PostMapping("/register")
    //账户注册
    public ResultVo add(@Valid UserForm userForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResultUtil.fail(bindingResult.getFieldError().getDefaultMessage());
        }
        try {
            User user = new User();
            BeanUtils.copyProperties(userForm,user);
            user.setRole(RoleEnum.USER.getRole());
            if(user.getUserIcon()==null){
                user.setUserIcon("/travel/userIcon/default.jpeg");
            }
            user.setPassword(bCryptPasswordEncoder.encode(userForm.getPassword()));
            //TODO 验证用户名或者邮箱是否已经注册
            userService.insert(user);
            return ResultUtil.success();
        }catch (Exception e){
            return ResultUtil.fail(e.getMessage());
        }
    }
    //通过查询邮箱和用户名来找回密码
    @GetMapping("/password/forget")
    public ResultVo getByEmailAndPhone(@Param("email")String email,@Param("username")String username){
        ResultVo resultVo = new ResultVo();
        try{
            userService.findByEmailAndUsername(email,username);
            return ResultUtil.success();
        }catch (Exception e){
            return ResultUtil.fail(e.getMessage());
        }
    }
    @GetMapping("/checkEmail")
    //检查邮箱是否已注册
    public ResultVo getByEmail(@Param("email")String email){
        boolean result = userService.isEmailExists(email);
        if(result){
            return ResultUtil.success();
        }else{
            return ResultUtil.fail(ResultEnum.EMAIL_REGISTERED.getMessage());
        }
    }
    //密码更改
    @PostMapping("/updatePassword")
    public ResultVo updatePassword(@Param("email")String email,@Param("phone")String username,@Param("password")String password){
        ResultVo resultVo = new ResultVo();
        try{
            boolean result = userService.updatePassword(email,username,bCryptPasswordEncoder.encode(password));
            if(result){
                return ResultUtil.success();
            }else{
                return ResultUtil.fail();
            }
        }catch (Exception e){
            return ResultUtil.fail(e.getMessage());
        }
    }
    @PostMapping("/user/userIcon")
    public ResultVo updateUserIcon(@Param("username")String username, @Param("file")MultipartFile file) {
        ResultVo resultVo = new ResultVo();
        try {
            boolean result = userService.updateUserIcon(username, file);
            if (result) {
                return ResultUtil.success();
            } else {
                return ResultUtil.fail();
            }
        } catch (Exception e) {
            return ResultUtil.fail(e.getMessage());
        }
    }
}
