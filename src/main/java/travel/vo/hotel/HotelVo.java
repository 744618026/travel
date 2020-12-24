package travel.vo.hotel;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.core.serializer.Serializer;
import travel.dao.hotel.Hotel;
import travel.dao.hotel.HotelImage;

import java.io.Serializable;
import java.util.List;

public class HotelVo implements Serializable{
    private static final long serialVersionUID = -5825585879228785770L;
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
    @JsonProperty("address")
    private String address;
    @JsonProperty("phone")
    private String phone;
    @JsonProperty("info")
    private String info;
    @JsonProperty("policy")
    private String policy;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getPolicy() {
        return policy;
    }

    public void setPolicy(String policy) {
        this.policy = policy;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

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
