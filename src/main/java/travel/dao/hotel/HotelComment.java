package travel.dao.hotel;

import java.util.Date;

public class HotelComment {
    /**酒店评论Id*/
    private String hotelCommentId;
    /**酒店Id*/
    private String hotelId;
    /**用户名*/
    private String userName;
    /**内容*/
    private String content;
    /**创建时间 */
    private Date createTime;
    public String getHotelCommentId() {
        return hotelCommentId;
    }

    public void setHotelCommentId(String hotelCommentId) {
        this.hotelCommentId = hotelCommentId;
    }

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
