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
import java.io.IOException;

@Service
public class UserServiceImpl implements UserService {
    private final String defaultIcon = "/travel/userIcon/default.jpeg";
    @Autowired
    private UserMapper userMapper;
    @Override
    public boolean insert(User user) {
        User user1 = userMapper.findByUserName(user.getUsername());
        if(user1!=null) {
            throw new SafetyException(ResultEnum.USER_EXISTS.getMessage());
        }
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
    @Override
    public User findByEmailAndUsername(String email, String username) {
        User user = userMapper.findByEmailAndUsername(email,username);
        if(user == null){
            throw new NullException(ResultEnum.USER_NOT_EXISTS.getMessage());
        }
        return user;
    }
    @Override
    public boolean isEmailExists(String email) {
        User user = userMapper.findByEmail(email);
        if(user == null){
            return false;
        }
        return true;
    }

    @Override
    public boolean updatePassword(String email,String username,String password) {
        User user = userMapper.findByEmailAndUsername(email,username);
        if(user == null){
            throw new NullException(ResultEnum.USER_NOT_EXISTS.getMessage());
        }
        int result = userMapper.updatePassword(email, username, password);
        if(result == 0){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public boolean updateUserIcon(String username, MultipartFile file) {
        User user = userMapper.findByUserName(username);
        if(user == null){
            throw new NullException(ResultEnum.USER_NOT_EXISTS.getMessage());
        }
        if(file.isEmpty()){
            throw new NullException(ResultEnum.UPLOAD_FILE_NULL.getMessage());
        }
            String path = Thread.currentThread().getContextClassLoader().getResource("").getPath();
            String resource = "static/userIcon";
            String oldUrl = user.getUserIcon();
            //删除原头像图片
            if(!oldUrl.equals(defaultIcon)){
                String fileName = user.getUserIcon().substring(user.getUserIcon().lastIndexOf("/"));
                File file1 = new File(path+resource+"/"+fileName);
                if(file1.exists()){
                    file1.delete();
                }
            }
            File dir = new File(path+resource);
            if(!dir.exists()){
                dir.mkdirs();
            }
            String newName = FileNameUtil.getNewFileName(file);
            File file1 = new File(path+resource+"/"+newName);
            try {
                file.transferTo(file1);
            }catch (Exception e){
                throw new NullException(e.getMessage());
            }
            String url = "/travel/userIcon/"+newName;
            int result = userMapper.updateUserIcon(username,url);
            if(result==0){
                return false;
            }
            return true;
    }

}
