package travel.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.io.Serializable;

public class RegionVo implements Serializable {
    private static final long serialVersionUID = -7678532546714224855L;
    @JsonProperty("regionId")
    private String regionId;

    @JsonProperty("name")
    private String name;
    public String getRegionId() {
        return regionId;
    }
    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
