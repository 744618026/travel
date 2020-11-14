package travel.dataForm;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;

public class POIForm {
    /**景点名称*/
    @NotEmpty(message = "景点名不能为空")
    private String poiName;
    /**景点描述*/

    private String poiDescribe;
    /**地区ID*/
    @NotEmpty(message = "景点所属地区不能为空")
    private String regionId;
    /**门票价格*/
    @NotEmpty(message = "门票价格不为空")
    private BigDecimal poiTicketPrice;
    /**门票库存*/
    @NotEmpty(message = "门票库存不为空")
    private Integer poiStock;

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
