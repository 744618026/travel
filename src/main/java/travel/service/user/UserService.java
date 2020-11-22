package travel.service.user;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;
import travel.dao.user.User;

import java.util.List;

public interface UserService {
    /**
     *
     * @param user
     * @return
     */
    boolean insert(User user);
    /**
     *
     * @param userName
     * @return
     */
    User findByUserName(String userName);
    /**
     *
     * @param email 邮箱
     * @param username 用户名
     * @return User
     */
    User findByEmailAndUsername(String email, String username);

    /**
     *
     * @param email
     * @return boolean
     */
    boolean isEmailExists(String email);

    /**
     *
     * @param password  密码
     * @return
     */
    boolean updatePassword(String email,String username,String password);

    /**
     *
     * @param username 用户名
     * @param file 头像文件
     * @return
     */
    boolean updateUserIcon(String username,MultipartFile file);
}
