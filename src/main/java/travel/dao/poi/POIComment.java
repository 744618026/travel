package travel.dao.poi;

import java.util.Date;

public class POIComment {
    /*评论ID**/
    private String poiCommentId;
    /**景点ID*/
    private String poiId;
    /**用户名*/
    private String userName;
    /**内容*/
    private String content;
    /**评论时间*/
    private Date createTime;

    public String getPoiCommentId() {
        return poiCommentId;
    }

    public void setPoiCommentId(String poiCommentId) {
        this.poiCommentId = poiCommentId;
    }

    public String getPoiId() {
        return poiId;
    }

    public void setPoiId(String poiId) {
        this.poiId = poiId;
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
