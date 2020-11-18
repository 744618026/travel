package travel.service.serviceImpl.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import travel.dao.user.User;
import travel.enums.ResultEnum;
import travel.exceptions.NullException;
import travel.exceptions.SafetyException;
import travel.mapper.user.UserMapper;
import travel.service.user.UserService;
import travel.utils.FileNameUtil;

import java.io.File;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public boolean insert(User user) {
        User user1 = userMapper.findByUserName(user.getUsername());
        if(user1!=null){
            throw new SafetyException(ResultEnum.USER_EXISTS.getMessage());
        }
        //TODO
        /**MultipartFile file;
        if(!file.isEmpty()){
            try {
                String path = Thread.currentThread().getContextClassLoader().getResource("").getPath();
                String resource = "static/userIcon";
                File dir = new File(path+resource);
                if(!dir.exists()){
                    dir.mkdirs();
                }
                String newName = FileNameUtil.getNewFileName(file);
                File file1 = new File(path+resource+"/"+newName);
                file.transferTo(file1);
                String url = "/travel/userIcon/"+newName;
                user.setUserIcon(url);
            }catch (Exception e){

                throw new NullException(e.getMessage());
            }
        }*/
        int result = userMapper.insert(user);
        if(result == 0){
            return false;
        }else {
            return true;
        }
    }
    @Override
    public User findByUserName(String userName) {
        User user = userMapper.findByUserName(userName);
        if(user == null){
            throw new NullException(ResultEnum.USER_NOT_EXISTS.getMessage());
        }
        return user;
    }
}
