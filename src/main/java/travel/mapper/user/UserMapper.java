package travel.mapper.user;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
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
}
