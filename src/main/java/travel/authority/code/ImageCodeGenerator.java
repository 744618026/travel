package travel.authority.code;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.ServletWebRequest;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

@Service
public class ImageCodeGenerator implements ValidateCodeGenerator{
    private String randString = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final int width = 95;
    private final int height = 25;
    private final int lineSize = 40;
    private final int num = 4;
    private Random random = new Random();
    @Override
    public ImageCode generate(ServletWebRequest request) {
        BufferedImage bufferedImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        Graphics g = bufferedImage.getGraphics();
        g.fillRect(0,0,width,height);
        g.setFont(new Font("Times New Roman",Font.ROMAN_BASELINE,18));
        g.setColor(getColor(110,133));
        for(int i=0;i<lineSize;i++){
            drawLine(g);
        }
        String code = "";
        for(int i=0;i<=num;i++){
            code = drawString(g,code,i);
        }
        g.dispose();

        return new ImageCode(bufferedImage,code,60);
    }

    private Color getColor(int fc,int bc){
        if(fc > 255){
            fc = 255;
        }
        if(bc>255){
            bc = 255;
        }
        int r = fc + random.nextInt(bc-fc-16);
        int b = fc + random.nextInt(bc-fc-14);
        int g = fc + random.nextInt(bc-fc-18);
        return new Color(r,b,g);
    }
    private String drawString(Graphics g,String code,int i){
        g.setFont(getFont());
        g.setColor(new Color(random.nextInt(100),random.nextInt(110),random.nextInt(120)));
        String sRand = String.valueOf(getRandomString(random.nextInt(randString.length())));
        code += sRand;
        g.translate(random.nextInt(3),random.nextInt(3));
        g.drawString(sRand,13*i,16);
        return code;
    }
    private void drawLine(Graphics g){
        int x = random.nextInt(width);
        int y = random.nextInt(height);
        int xl = random.nextInt(13);
        int yl = random.nextInt(15);
        g.drawLine(x,y,x+xl,y+yl);
    }
    private String getRandomString(int num) {
        return String.valueOf(randString.charAt(num));
    }
    private Font getFont() {
        return new Font("Fixedsys", Font.CENTER_BASELINE, 18);
    }
}
