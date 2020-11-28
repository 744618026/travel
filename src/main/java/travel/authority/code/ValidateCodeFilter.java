package travel.authority.code;

import io.netty.util.internal.StringUtil;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;
import travel.controller.CodeController;
import travel.enums.ReturnMessageEnum;
import travel.exceptions.ValidateCodeException;
import travel.vo.ResultVo;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashSet;
import java.util.Set;

public class ValidateCodeFilter extends OncePerRequestFilter{
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
    private Set<String> urls = new HashSet<>();
    private AntPathMatcher pathMatcher = new AntPathMatcher();
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        urls.add("/travel/checkUser");
        urls.add("/travel/login");
        urls.add("/travel/register");
        boolean action = false;
        for(String url : urls){
            if(pathMatcher.match(url,httpServletRequest.getRequestURI())){
                action = true;
            }
        }
        if(action){
            try {
                validate(new ServletWebRequest(httpServletRequest));
            }catch (Exception e){

                httpServletResponse.setCharacterEncoding("Utf-8");
                httpServletResponse.setContentType("application/json;charset=utf-8");
                ResultVo resultVo = new ResultVo();
                resultVo.setCode(ReturnMessageEnum.FAILED.getCode());
                resultVo.setMessage(e.getMessage());
                OutputStream outputStream = httpServletResponse.getOutputStream();
                outputStream.write(resultVo.toString().getBytes());
                outputStream.close();
                //httpServletResponse.reset();
            }
        }
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }
    private void validate(ServletWebRequest request) throws ServletRequestBindingException, ValidateCodeException {
        ImageCode codeSession = (ImageCode) sessionStrategy.getAttribute(request,CodeController.SESSION_KEY);
        String codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(),"imageCode").toUpperCase();
        if(StringUtil.isNullOrEmpty(codeInRequest)){
            throw new ValidateCodeException("验证码的值不能为空");
        }
        if(codeSession==null){
            throw new ValidateCodeException("验证码不存在");
        }
        if (codeSession.IsExpired()) {
            sessionStrategy.removeAttribute(request, CodeController.SESSION_KEY);
            throw new ValidateCodeException("验证码已过期");
        }
        if (!codeSession.getCode().equals(codeInRequest)) {
            throw new ValidateCodeException("验证码不匹配");
        }
        sessionStrategy.removeAttribute(request, CodeController.SESSION_KEY);
    }
}
