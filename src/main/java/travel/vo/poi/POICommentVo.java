package travel.vo.poi;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;

public class POICommentVo implements Serializable{
    @JsonProperty("commentId")
    private String poiCommentId;
    /**景点ID*/
    @JsonProperty("poiId")
    private String poiId;
    /**用户名*/
    @JsonProperty("userName")
    private String userName;
    /**内容*/
    @JsonProperty("content")
    private String content;
    /**评论时间*/
    @JsonProperty("createTime")
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
