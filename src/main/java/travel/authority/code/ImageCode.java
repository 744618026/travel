package travel.authority.code;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class ImageCode {
    private BufferedImage image;
    private String code;
    private LocalDateTime expireTime;
    public ImageCode(BufferedImage image,String code,int expireIn){
        this.image = image;
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }
    public boolean IsExpired(){
        return LocalDateTime.now().isAfter(expireTime);
    }
    public BufferedImage getImage() {
        return image;
    }

    public String getCode() {
        return code;
    }

    public LocalDateTime getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(LocalDateTime expireTime) {
        this.expireTime = expireTime;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
