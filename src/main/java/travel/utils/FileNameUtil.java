package travel.utils;

import org.springframework.web.multipart.MultipartFile;

import java.util.Random;

public class FileNameUtil {
    public static String getNewFileName(MultipartFile file){
        String oldFileName = file.getOriginalFilename();
        String extension = oldFileName.substring(oldFileName.lastIndexOf("."));
        String newName = getFileName() + extension;
        return newName;
    }
    private static synchronized String getFileName(){
        Random random = new Random();
        Integer integer = random.nextInt(90000) + 10000;
        return System.currentTimeMillis() + integer.toString();
    }
}
