package travel.authority;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import travel.dao.user.User;
import travel.mapper.user.UserMapper;

@Service
public class MyUserDetailService implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userMapper.findByUserName(s);
        if(user == null){
            throw new UsernameNotFoundException("找不到该用户");
        }
        return new JwtUser(user);
    }
}
