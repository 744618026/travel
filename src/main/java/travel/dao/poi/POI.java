package travel.dao.poi;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 景点
 */

public class POI {
    /**景点ID*/
    private String poiId;
    /**景点名称*/
    private String poiName;
    /**景点描述*/
    private String poiDescribe;
    /**地区ID*/
    private String regionId;
    /**门票价格*/
    private BigDecimal poiTicketPrice;
    /**门票库存*/
    private Integer poiStock;
    /**创建时间*/
    private Date createTime;
    /**更新时间*/
    private Date updateTime;
    public String getPIOId() {
        return poiId;
    }

    public void setPIOId(String poiId) {
        this.poiId = poiId;
    }

    public String getPIOName() {
        return poiName;
    }

    public void setPIOName(String poiName) {
        this.poiName = poiName;
    }

    public String getPIODescribe() {
        return poiDescribe;
    }

    public void setPIODescribe(String poiDescribe) {
        this.poiDescribe = poiDescribe;
    }

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public BigDecimal getPIOTicketPrice() {
        return poiTicketPrice;
    }

    public void setPIOTicketPrice(BigDecimal poiTicketPrice) {
        this.poiTicketPrice = poiTicketPrice;
    }

    public Integer getPIO_Stock() {
        return poiStock;
    }

    public void setPIO_Stock(Integer poiStock) {
        this.poiStock = poiStock;
    }
}
