package travel.dao;

import java.util.Date;

/**
 * 景点
 */

public class PIO {
    /**景点ID*/
    private String PIOId;
    /**景点名称*/
    private String PIOName;
    /**景点描述*/
    private String PIODescribe;
    /**地区ID*/
    private String RegionId;
    /**创建时间*/
    private Date CreateTime;
    /**更新时间*/
    private Date UpdateTime;

    public String getPIOId() {
        return PIOId;
    }

    public void setPIOId(String PIOId) {
        this.PIOId = PIOId;
    }

    public String getPIOName() {
        return PIOName;
    }

    public void setPIOName(String PIOName) {
        this.PIOName = PIOName;
    }

    public String getPIODescribe() {
        return PIODescribe;
    }

    public void setPIODescribe(String PIODescribe) {
        this.PIODescribe = PIODescribe;
    }

    public String getRegionId() {
        return RegionId;
    }

    public void setRegionId(String regionId) {
        RegionId = regionId;
    }

    public Date getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Date createTime) {
        CreateTime = createTime;
    }

    public Date getUpdateTime() {
        return UpdateTime;
    }

    public void setUpdateTime(Date updateTime) {
        UpdateTime = updateTime;
    }
}
