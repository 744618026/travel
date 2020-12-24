package travel.vo.hotel;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class HotelImageVo implements Serializable {
    private static final long serialVersionUID = -4625821840500647106L;
    @JsonProperty("id")
    private String imageId;
    @JsonProperty("url")
    private String url;
    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
