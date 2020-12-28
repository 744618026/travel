package travel.utils;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

public class FileUploadUtil {
    public static boolean upload(HttpServletRequest request, MultipartFile file,String resource,String fileName) throws IOException {
        String path = request.getServletContext().getRealPath("");;
        File dir = new File(path + resource);
        if (!dir.exists()) {
            boolean result = dir.mkdirs();
            if(!result){
                return false;
            }
        }
        File file1 = new File(path + resource + "/" + fileName);
        file.transferTo(file1);
        return true;
    }
}
