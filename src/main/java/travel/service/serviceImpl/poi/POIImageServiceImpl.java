package travel.service.serviceImpl.poi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import travel.dao.poi.POI;
import travel.dao.poi.POIImage;
import travel.enums.ResultEnum;
import travel.exceptions.NullException;
import travel.mapper.poi.POIImageMapper;
import travel.mapper.poi.POIMapper;
import travel.service.poi.POIImageService;

import java.io.File;
import java.util.List;
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
    public boolean insert(POIImage poiImage) {
        POI poi = poiMapper.findByPOIId(poiImage.getPoiId());
        if(poi == null){
            throw new NullException(ResultEnum.POI_NOT_EXISTS.getMessage());
        }
        int result = poiImageMapper.insert(poiImage);
        if(result == 0){
            return false;
        }else {
            return true;
        }
    }

    @Override
    public boolean delete(Integer imageId) {
        POIImage poiImage = poiImageMapper.findByImageId(imageId);
        if(poiImage == null){
            throw new NullException(ResultEnum.IMAGE_NOT_EXISTS.getMessage());
        }
        int result = poiImageMapper.delete(imageId);
        if(result == 0){
            return false;
        }else {
            return true;
        }
    }
}
