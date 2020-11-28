package travel.authority;

import org.springframework.cglib.core.Local;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import travel.enums.ReturnMessageEnum;
import travel.vo.ResultVo;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;
import java.util.Locale;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;
    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        super.setFilterProcessesUrl("/login");
    }
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username,password)
            );
    }
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        JwtUser jwtUser = (JwtUser) authResult.getPrincipal();
        String role = "";
        Collection<? extends GrantedAuthority> authorities = jwtUser.getAuthorities();
        for(GrantedAuthority authority : authorities){
            role = authority.getAuthority();
        }
        String token = JwtUtils.createToken(jwtUser.getUsername(),role);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        String tokenStr = JwtUtils.TOKEN_PREFIX + token;
        response.setHeader("token",tokenStr);
    }
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        OutputStream outputStream = response.getOutputStream();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        ResultVo resultVo = new ResultVo<>();
        resultVo.setCode(ReturnMessageEnum.FAILED.getCode());
        resultVo.setMessage(messageSourceService().getMessage(failed.getMessage()));
        outputStream.write(resultVo.toString().getBytes());
        outputStream.close();
    }
    private ReloadableResourceBundleMessageSource messageSource(){
        Locale.setDefault(Locale.CHINA);
        ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource = new ReloadableResourceBundleMessageSource();
        reloadableResourceBundleMessageSource.setBasename("classpath:messages");
        reloadableResourceBundleMessageSource.setUseCodeAsDefaultMessage(true);
        reloadableResourceBundleMessageSource.setDefaultEncoding("UTF-8");
        return reloadableResourceBundleMessageSource;
    }
    private MessageSourceService messageSourceService(){
        return new MessageSourceService(messageSource());
    }

}
