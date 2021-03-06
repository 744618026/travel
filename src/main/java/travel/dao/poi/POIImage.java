package travel.dao.poi;

public class POIImage {
    /**图片ID*/
    private Integer poiImageId;
    /**景点ID**/
    private String poiId;
    /**景点图片Url**/
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
