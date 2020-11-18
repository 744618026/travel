package travel.dataForm;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class UserForm {
    @NotEmpty(message = "用户名不能为空")
    /**用户名*/
    private String username;
    /**昵称*/
    private String nickname;;
    /**密码*/
    @NotEmpty(message = "密码不能为空")
    private String password;

    @NotEmpty(message = "邮箱不能为空")
    /**邮箱*/
    private String email;
    /**电话*/
    @NotEmpty(message = "电话不能为空")
    private String phone;
    /**出身日期*/
    @NotNull(message = "出身日期不能为空")
    private Date bornDate;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getBornDate() {
        return bornDate;
    }

    public void setBornDate(Date bornDate) {
        this.bornDate = bornDate;
    }
}
