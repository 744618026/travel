package travel.dao.folkways;

public class Folkways {
    /**民俗Id*/
    private String folkwaysId;
    /**民俗名称*/
    private String folkwaysName;
    /**地区Id*/
    private String regionId;
    /**民俗内容*/
    private String folkwaysContent;

    public String getFolkwaysId() {
        return folkwaysId;
    }

    public void setFolkwaysId(String folkwaysId) {
        this.folkwaysId = folkwaysId;
    }

    public String getFolkwaysName() {
        return folkwaysName;
    }

    public void setFolkwaysName(String folkwaysName) {
        this.folkwaysName = folkwaysName;
    }

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    public String getFolkwaysContent() {
        return folkwaysContent;
    }

    public void setFolkwaysContent(String folkwaysContent) {
        this.folkwaysContent = folkwaysContent;
    }
}
