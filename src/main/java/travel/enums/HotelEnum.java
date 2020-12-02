package travel.enums;

public enum HotelEnum {
    HOTEL_IMAGE(0,"hotel");

    private Integer integer;
    private String name;
    HotelEnum(Integer integer, String name) {
        this.integer = integer;
        this.name = name;
    }

    public Integer getInteger() {
        return integer;
    }

    public String getName() {
        return name;
    }
}
