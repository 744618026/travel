package travel.utils;

import java.util.Random;

public class POIHotelKeyUtil {
    public static  synchronized String getKey(){
        Random random = new Random();
        Integer number =  random.nextInt(400000)+100000;
        return  System.currentTimeMillis() + String.valueOf(number);
    }
}
