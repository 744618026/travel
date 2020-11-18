package travel.enums;

public enum RoleEnum {
    ADMIN(1,"ROLE_ADMIN"),
    USER(2,"ROLE_USER");
    private Integer code;
    private String role;

    RoleEnum(Integer code, String role) {
        this.code = code;
        this.role = role;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
