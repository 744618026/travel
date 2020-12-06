package travel.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.io.Serializable;

public class RegionVo implements Serializable {
    private static final long serialVersionUID = -7678532546714224855L;
    @JsonProperty("regionId")
    private String regionId;

    @JsonProperty("name")
    private String regionName;
    @JsonProperty("province")
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
