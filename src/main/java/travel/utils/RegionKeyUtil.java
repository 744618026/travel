package travel.utils;

import java.util.Random;

public class RegionKeyUtil {
    public synchronized static String getKey(){
        Random random = new Random();
        Integer number =  random.nextInt(90)+10;
        return  System.currentTimeMillis() + String.valueOf(number);
    }
}
