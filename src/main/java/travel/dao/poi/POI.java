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

    public String getPoiId() {
        return poiId;
    }

    public void setPoiId(String poiId) {
        this.poiId = poiId;
    }

    public String getPoiName() {
        return poiName;
    }

    public void setPoiName(String poiName) {
        this.poiName = poiName;
    }

    public String getPoiDescribe() {
        return poiDescribe;
    }

    public void setPoiDescribe(String poiDescribe) {
        this.poiDescribe = poiDescribe;
    }

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    public BigDecimal getPoiTicketPrice() {
        return poiTicketPrice;
    }

    public void setPoiTicketPrice(BigDecimal poiTicketPrice) {
        this.poiTicketPrice = poiTicketPrice;
    }

    public Integer getPoiStock() {
        return poiStock;
    }

    public void setPoiStock(Integer poiStock) {
        this.poiStock = poiStock;
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
}
