package travel.controller.admin;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class LoginController {
    @RequestMapping(method = RequestMethod.GET,value = "/adminLogin")
    public ModelAndView adminLogin(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/adminLogin/adminLogin.html");
        return mv;
    }
}
