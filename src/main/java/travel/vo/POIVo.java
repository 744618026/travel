package travel.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import travel.dao.poi.POIImage;

import java.math.BigDecimal;
import java.util.List;

public class POIVo {
    @JsonProperty("poiId")
    private String poiId;
    /**景点名称*/
    @JsonProperty("name")
    private String poiName;
    /**景点描述*/
    @JsonProperty("describe")
    private String poiDescribe;
    /**地区ID*/
    @JsonProperty("regionId")
    private String regionId;
    /**门票价格*/
    @JsonProperty("ticketPrice")
    private BigDecimal poiTicketPrice;
    /**门票库存*/
    @JsonProperty("ticketStock")
    private Integer poiStock;
    @JsonProperty("image")
    private List<POIImageVo> poiImageList;

    public List<POIImageVo> getPoiImageList() {
        return poiImageList;
    }

    public void setPoiImageList(List<POIImageVo> poiImageList) {
        this.poiImageList = poiImageList;
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
