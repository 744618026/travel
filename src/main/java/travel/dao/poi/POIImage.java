package travel.dao.poi;

public class POIImage {
    /**图片ID*/
    private String POIImageId;
    /**景点ID**/
    private String POIId;
    /**景点图片Url**/
    private String POIImageUrl;

    public String getPOIImageId() {
        return POIImageId;
    }

    public void setPOIImageId(String POIImageId) {
        this.POIImageId = POIImageId;
    }

    public String getPOIId() {
        return POIId;
    }

    public void setPOIId(String POIId) {
        this.POIId = POIId;
    }

    public String getPOIImageUrl() {
        return POIImageUrl;
    }

    public void setPOIImageUrl(String POIImageUrl) {
        this.POIImageUrl = POIImageUrl;
    }
}
