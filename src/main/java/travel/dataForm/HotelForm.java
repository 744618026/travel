package travel.dataForm;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class HotelForm {
    private String hotelId;
    @NotBlank(message = "酒店名称不能为空")
    private String hotelName;
    /**酒店描述*/
    private String hotelDescribe;
    /**地区Id**/
    @NotBlank(message = "地区Id不能为空")
    private String regionId;
    @NotBlank(message = "地址不能为空")
    private String address;
    @NotBlank(message = "联系电话不为空")
    private String phone;
    @NotBlank(message = "酒店信息不为空")
    private String info;
    @NotBlank(message = "酒店政策不为空")
    private String policy;


    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

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
