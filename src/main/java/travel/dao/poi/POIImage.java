package travel.dao.poi;

public class POIImage {
    /**图片ID*/
    private String poiImageId;
    /**景点ID**/
    private String poiId;
    /**景点图片Url**/
    private String poiImageUrl;

    public String getPOIImageId() {
        return poiImageId;
    }

    public void setPOIImageId(String poiImageId) {
        this.poiImageId = poiImageId;
    }

    public String getPOIId() {
        return poiId;
    }

    public void setPOIId(String POIId) {
        this.poiId = POIId;
    }

    public String getPOIImageUrl() {
        return poiImageUrl;
    }

    public void setPOIImageUrl(String poiImageUrl) {
        this.poiImageUrl = poiImageUrl;
    }
}
