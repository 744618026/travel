package travel.service.serviceImpl.poi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import travel.dao.poi.POI;
import travel.dao.poi.POIImage;
import travel.enums.ResultEnum;
import travel.exceptions.NullException;
import travel.mapper.poi.POIImageMapper;
import travel.mapper.poi.POIMapper;
import travel.service.poi.POIImageService;

import java.io.File;
import java.util.List;
import java.util.Random;

@Service
public class POIImageServiceImpl implements POIImageService {
    @Autowired
    private POIImageMapper poiImageMapper;
    @Autowired
    private POIMapper poiMapper;
    @Override
    public List<POIImage> findByPOIId(String POIId) {
        List<POIImage> poiImageList = poiImageMapper.findByPOIId(POIId);
        if(poiImageList.size()==0){
            throw new NullException(ResultEnum.DATA_GET_NULL.getMessage());
        }
        return poiImageList;
    }
    @Override
    public POIImage findByImageId(Integer ImageId) {
        return poiImageMapper.findByImageId(ImageId);
    }
    @Override
    public boolean insert(POIImage poiImage, MultipartFile file) {
        POI poi = poiMapper.findByPOIId(poiImage.getPoiId());
        if (poi == null) {
            throw new NullException(ResultEnum.POI_NOT_EXISTS.getMessage());
        }
        if (file.isEmpty()) {
            throw new NullException(ResultEnum.UPLOAD_FILE_NULL.getMessage());
        }
        try {
            String path = Thread.currentThread().getContextClassLoader().getResource("").getPath();
            String resource = "static/poiImage/" + poiImage.getPoiId() + "/";
            File dir = new File(path + resource);
            if (!dir.exists()) {
                dir.mkdir();
            }
            String fileName = getNewFileName(file);
            File file1 = new File(path + dir + fileName);
            file.transferTo(file1);
            String url = "/travel/poiImage/"+poiImage.getPoiId()+"/"+fileName;
            poiImage.setPoiImageUrl(url);
            int result = poiImageMapper.insert(poiImage);
            if (result == 0) {
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            //TODO
            throw new NullException(e.getMessage());
        }
    }
    @Override
    public boolean delete(Integer imageId) {
        POIImage poiImage = poiImageMapper.findByImageId(imageId);
        if(poiImage == null){
            throw new NullException(ResultEnum.IMAGE_NOT_EXISTS.getMessage());
        }
        String url = poiImage.getPoiImageUrl();
        String fileName = url.substring(url.lastIndexOf("/"));
        String path = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        String resource = "static/poiImage/"+poiImage.getPoiId()+"/";
        File file  = new File(path+resource+fileName);
        if(file.exists()){
            file.delete();
        }
        int result = poiImageMapper.delete(imageId);
        if(result == 0){
            return false;
        }else {
            return true;
        }
    }
    private static String getNewFileName(MultipartFile  file){
        String oldFileName = file.getName();
        String extension = oldFileName.substring(oldFileName.lastIndexOf("."));
        String newName = getFileName() + "." + extension;
        return newName;
    }
    private static synchronized String getFileName(){
        Random random = new Random();
        Integer integer = random.nextInt(90000) + 10000;
        return System.currentTimeMillis() + integer.toString();
    }
}
