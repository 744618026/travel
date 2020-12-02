package travel.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HotelImageVo {
    @JsonProperty("id")
    private String imageId;
    @JsonProperty("category")
    private int category;
    @JsonProperty("url")
    private String url;
    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
