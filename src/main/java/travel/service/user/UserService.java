package travel.service.user;

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
}
