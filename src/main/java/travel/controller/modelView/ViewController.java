package travel.controller.modelView;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
@RestController
public class ViewController {
    @RequestMapping(method = RequestMethod.GET,value = "/forgetPwd")
    public ModelAndView forget(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/forgetPwd/ForgetPassword.html");
        return mv;
    }
    @RequestMapping(method = RequestMethod.GET,value = "/userRegister")
    public ModelAndView register(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/regist/regist.html");
        return mv;
    }
}
