package travel.dao.poi;

import java.util.Date;

public class POIComment {
    /*评论ID**/
    private String POICommentId;
    /**景点ID*/
    private String POIId;
    /**用户名*/
    private String userName;
    /**内容*/
    private String content;
    /**评论时间*/
    private Date createTime;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getPOICommentId() {
        return POICommentId;
    }

    public void setPOICommentId(String POICommentId) {
        this.POICommentId = POICommentId;
    }

    public String getPOIId() {
        return POIId;
    }

    public void setPOIId(String POIId) {
        this.POIId = POIId;
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
}
