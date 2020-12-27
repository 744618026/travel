package travel.service.serviceImpl.poi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import travel.utils.FileNameUtil;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;
import java.util.Random;

@Service
public class POIImageServiceImpl implements POIImageService {
    @Autowired
    private POIImageMapper poiImageMapper;
    @Autowired
    private POIMapper poiMapper;
    private Logger LOG = LoggerFactory.getLogger(POIImageServiceImpl.class);
    @Override
    public List<POIImage> findByPOIId(String POIId) {
        List<POIImage> poiImageList = poiImageMapper.findByPOIId(POIId);
        return poiImageList;
    }
    @Override
    public POIImage findByImageId(Integer ImageId) {
        return poiImageMapper.findByImageId(ImageId);
    }
    @Override
    public boolean insert(POIImage poiImage, MultipartFile file, HttpServletRequest request) {
        POI poi = poiMapper.findByPOIId(poiImage.getPoiId());
        if (poi == null) {
            throw new NullException(ResultEnum.POI_NOT_EXISTS.getMessage());
        }
        if (file.isEmpty()) {
            throw new NullException(ResultEnum.UPLOAD_FILE_NULL.getMessage());
        }
        try {
            String path = request.getServletContext().getRealPath("");
            String resource = "poiImage/" + poiImage.getPoiId();
            File dir = new File(path + resource);
            if (!dir.exists()) {
                boolean result = dir.mkdirs();
                if(!result){
                    return false;
                }
            }
            String fileName = FileNameUtil.getNewFileName(file);
            File file1 = new File(path + resource +"/"+ fileName);
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
            LOG.info(e.getMessage());
            throw new NullException(e.getMessage());
        }
    }
    @Override
    public boolean deleteByImageId(Integer imageId,HttpServletRequest request) {
        POIImage poiImage = poiImageMapper.findByImageId(imageId);
        if(poiImage == null){
            throw new NullException(ResultEnum.IMAGE_NOT_EXISTS.getMessage());
        }
        String url = poiImage.getPoiImageUrl().replace("/travel/","");
        String path = request.getServletContext().getRealPath("");
        File file  = new File(path+url);
        if(file.exists()){
            file.delete();
        }
        int result = poiImageMapper.deleteByImageId(imageId);
        if(result == 0){
            return false;
        }else {
            return true;
        }
    }
}
