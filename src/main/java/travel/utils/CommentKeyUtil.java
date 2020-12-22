package travel.utils;

import java.util.Random;

public class CommentKeyUtil {
    public static synchronized String getUniqueKey(){
        Random random = new Random();

        Integer number =  random.nextInt(9000000)+1000000;

        return  System.currentTimeMillis() + String.valueOf(number);
    }
}
