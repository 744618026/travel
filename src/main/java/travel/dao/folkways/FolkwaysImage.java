package travel.dao.folkways;

public class FolkwaysImage {
    /*民俗Id**/
    private String folkwaysId;
    /**图片Id*/
    private Integer folkwaysImageId;
    /*图片地址**/
    private String folkwaysImageUrl;

    public String getFolkwaysId() {
        return folkwaysId;
    }

    public void setFolkwaysId(String folkwaysId) {
        this.folkwaysId = folkwaysId;
    }

    public Integer getFolkwaysImageId() {
        return folkwaysImageId;
    }

    public void setFolkwaysImageId(Integer folkwaysImageId) {
        this.folkwaysImageId = folkwaysImageId;
    }

    public String getFolkwaysImageUrl() {
        return folkwaysImageUrl;
    }

    public void setFolkwaysImageUrl(String folkwaysImageUrl) {
        this.folkwaysImageUrl = folkwaysImageUrl;
    }
}
