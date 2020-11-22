package travel.mapper.user;

import org.apache.ibatis.annotations.*;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;
import travel.dao.user.User;

@Repository
@Mapper
public interface UserMapper {
    /**
     *
     * @param userName
     * @return
     */
    @Select("Select * from user where userName=#{username}")
    User findByUserName(String userName);
    /**
     *
     * @param user user对象
     * @return int
     */
    @Insert("Insert into user(username,password,userIcon,email,phone,bornDate,nickname,role)" +
            "values(#{username},#{password},#{userIcon},#{email},#{phone},#{bornDate},#{nickname},#{role})")
    int insert(User user);

    /**
     *
     * @param email 邮箱
     * @param username 电话
     * @return User
     */
    @Select("Select * from user where email=#{email} and username=#{username}")
    User findByEmailAndUsername(@Param("email")String email,@Param("username")String username);

    /**
     *
     * @param email 邮箱
     * @return String
     */
    @Select("Select * from user where email=#{email}")
    User findByEmail(@Param("email") String email);

    /**
     *
     * @param email 邮箱
     * @param username 用户名
     * @param password 新密码
     * @return
     */
    @Update("update user set password=#{password} where email=#{email} and username=#{username}")
    int updatePassword(@Param("email")String email,@Param("username")String username,@Param("password")String password);

    /**
     *
     * @param username 用户名
     * @return int
     */
    @Update("update user set userIcon=#{userIcon} where username=#{username}")
    int updateUserIcon(@Param("username") String username, @Param("userIcon")String userIcon);
}
