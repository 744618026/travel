package travel.dao.folkways;

public class Folkways {
    /**民俗Id*/
    private String FolkwaysId;
    /**民俗名称*/
    private String FolkwaysName;
    /**地区Id*/
    private String RegionId;
    /**民俗内容*/
    private String FolkwaysContent;

    public String getFolkwaysId() {
        return FolkwaysId;
    }

    public void setFolkwaysId(String folkwaysId) {
        FolkwaysId = folkwaysId;
    }

    public String getFolkwaysName() {
        return FolkwaysName;
    }

    public void setFolkwaysName(String folkwaysName) {
        FolkwaysName = folkwaysName;
    }

    public String getRegionId() {
        return RegionId;
    }

    public void setRegionId(String regionId) {
        RegionId = regionId;
    }

    public String getFolkwaysContent() {
        return FolkwaysContent;
    }

    public void setFolkwaysContent(String folkwaysContent) {
        FolkwaysContent = folkwaysContent;
    }
}
