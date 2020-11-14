package travel.dao.hotel;

public class HotelCategory {
    /*类目Id**/
    private String categoryId;
    /*类目名称**/
    private String categoryName;
    /**类目类型*/
    private Integer categoryType;

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(Integer categoryType) {
        this.categoryType = categoryType;
    }
}
