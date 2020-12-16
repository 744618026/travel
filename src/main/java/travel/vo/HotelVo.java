package travel.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.core.serializer.Serializer;
import travel.dao.hotel.Hotel;
import travel.dao.hotel.HotelImage;

import java.io.Serializable;
import java.util.List;

public class HotelVo implements Serializable {
    private static final long serialVersionUID = -4044478237212554787L;
    @JsonProperty("hotelId")
    private String hotelId;
    /**酒店名*/
    @JsonProperty("hotelName")
    private String hotelName;
    /**酒店描述*/
    @JsonProperty("hotelDescribe")
    private String hotelDescribe;
    /**地区Id**/
    @JsonProperty("regionId")
    private String regionId;

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getHotelDescribe() {
        return hotelDescribe;
    }

    public void setHotelDescribe(String hotelDescribe) {
        this.hotelDescribe = hotelDescribe;
    }

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }
}
