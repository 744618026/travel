package travel.dataForm;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class HotelForm {
    @NotBlank(message = "酒店名称不能为空")
    private String hotelName;
    /**酒店描述*/
    private String hotelDescribe;
    /**地区Id**/
    @NotBlank(message = "地区Id不能为空")
    private String regionId;
    @NotBlank(message = "地址不能为空")
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
