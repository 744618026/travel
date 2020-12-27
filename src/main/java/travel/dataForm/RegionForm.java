package travel.dataForm;

import javax.validation.constraints.NotEmpty;

public class RegionForm {
    @NotEmpty(message = "id不为空")
    private String regionId;
    @NotEmpty(message = "地区名不为空")
    private String regionName;
    @NotEmpty(message = "省份不为空")
    private String province;

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }
}
