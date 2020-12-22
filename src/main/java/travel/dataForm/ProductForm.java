package travel.dataForm;

import javax.validation.constraints.NotEmpty;

public class ProductForm {
    @NotEmpty(message = "商品名不能为空")
    private String productName;
    @NotEmpty(message = "商品价格不能为空")
    private String productPrice;
    @NotEmpty(message = "商品库存不能为空")
    private String productStock;
    @NotEmpty(message = "酒店Id不能为空")
    private String hotelId;
    @NotEmpty(message = "床型不能为空")
    private String bedType;
    @NotEmpty(message = "上网方式不能为空")
    private String internet;
    @NotEmpty(message = "是否带喂鱼不能为空")
    private String bath;
    @NotEmpty(message = "房间面积不能为空")
    private String area;
    @NotEmpty(message = "房间是否带窗不能为空")
    private String window;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductStock() {
        return productStock;
    }

    public void setProductStock(String productStock) {
        this.productStock = productStock;
    }

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public String getBedType() {
        return bedType;
    }

    public void setBedType(String bedType) {
        this.bedType = bedType;
    }

    public String getInternet() {
        return internet;
    }

    public void setInternet(String internet) {
        this.internet = internet;
    }

    public String getBath() {
        return bath;
    }

    public void setBath(String bath) {
        this.bath = bath;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getWindow() {
        return window;
    }

    public void setWindow(String window) {
        this.window = window;
    }
}
