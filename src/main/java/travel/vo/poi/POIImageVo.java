package travel.vo.poi;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class POIImageVo implements Serializable{
    private static final long serialVersionUID = -159801947318150987L;
    @JsonProperty("imageId")
    private Integer poiImageId;
    /**景点ID**/
    @JsonProperty("poiId")
    private String poiId;
    /**景点图片Url**/
    @JsonProperty("url")
    private String poiImageUrl;

    public Integer getPoiImageId() {
        return poiImageId;
    }

    public void setPoiImageId(Integer poiImageId) {
        this.poiImageId = poiImageId;
    }

    public String getPoiId() {
        return poiId;
    }

    public void setPoiId(String poiId) {
        this.poiId = poiId;
    }

    public String getPoiImageUrl() {
        return poiImageUrl;
    }

    public void setPoiImageUrl(String poiImageUrl) {
        this.poiImageUrl = poiImageUrl;
    }
}
