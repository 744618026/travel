package travel.dataForm;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class POIForm {
    private String poiId;
    /**景点名称*/
    @NotNull(message = "景点名不能为空")
    private String poiName;
    /**景点描述*/
    private String poiDescribe;
    /**地区ID*/
    @NotNull(message = "景点所属地区不能为空")
    private String oldRegionId;
    /**门票价格*/
    @NotNull(message = "门票价格不为空")
    private BigDecimal poiTicketPrice;
    /**门票库存*/
    @NotNull(message = "门票库存不为空")
    private Integer poiStock;

    private String regionId;

    public String getOldRegionId() {
        return oldRegionId;
    }

    public void setOldRegionId(String oldRegionId) {
        this.oldRegionId = oldRegionId;
    }

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
}
