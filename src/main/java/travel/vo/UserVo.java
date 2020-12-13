package travel.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;

public class UserVo implements Serializable {
    private static final long serialVersionUID = 4997158275300836623L;
    @JsonProperty("username")
    private String username;
    /**昵称*/
    @JsonProperty("nickname")
    private String nickname;
    /**头像地址*/
    @JsonProperty("userIcon")
    private String userIcon;
    /**邮箱*/
    @JsonProperty("email")
    private String email;
    /**电话*/
    @JsonProperty("phone")
    private String phone;
    /**出身日期*/
    @JsonProperty("born")
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

    public String getUserIcon() {
        return userIcon;
    }

    public void setUserIcon(String userIcon) {
        this.userIcon = userIcon;
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
