package travel.service.serviceImpl.poi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import travel.dao.poi.POI;
import travel.dao.poi.POIImage;
import travel.dao.region.Region;
import travel.enums.ResultEnum;
import travel.exceptions.NullException;
import travel.mapper.poi.POIImageMapper;
import travel.mapper.poi.POIMapper;
import travel.service.poi.POIService;
import travel.utils.POIKeyUtil;

import java.io.File;
import java.util.List;

@Service
public class POIServiceImpl implements POIService {
    @Autowired
    private POIMapper POIMapper;
    @Autowired
    private POIImageMapper poiImageMapper;
    @Override
    public List<POI> findByRegionId(String regionId,Integer page,Integer size) {
        List<POI> poiList = POIMapper.findByRegionId(regionId);
        if(poiList.size()==0){
            throw new NullException(ResultEnum.DATA_GET_NULL.getMessage());
        }
        int startIndex = (page-1)*size;
        int endIndex = page*size;
        if(poiList.size()<endIndex || endIndex>poiList.size()){
            endIndex = poiList.size();
        }
        return poiList.subList(startIndex,endIndex);
    }

    @Override
    public Integer findByRegionId(String regionId) {
        List<POI> poiList = POIMapper.findByRegionId(regionId);
        if(poiList.size()==0){
            throw new NullException(ResultEnum.DATA_GET_NULL.getMessage());
        }
        return poiList.size();
    }
    @Override
    public POI findByPOIId(String poiId) {
        POI poi = POIMapper.findByPOIId(poiId);
        if(poi == null){
            throw new NullException(ResultEnum.POI_NOT_EXISTS.getMessage());
        }
        return poi;
    }

    @Override
    public  boolean insert(POI poi) {
        poi.setPoiId(POIKeyUtil.getKey());
        int result = POIMapper.insert(poi);
        if(result ==0){
            return false;
        }
        return true;
    }
    @Override
    public  boolean delete(String poiId) {
        POI poi = POIMapper.findByPOIId(poiId);
        if(poi == null){
            throw new NullException(ResultEnum.POI_NOT_EXISTS.getMessage());
        }
        List<POIImage> poiImageList = poiImageMapper.findByPOIId(poi.getPoiId());
        for(POIImage poiImage : poiImageList){
            String path = Thread.currentThread().getContextClassLoader().getResource("").getPath();
            String resource = "static/poiImage/";
            String fileName = poiImage.getPoiImageUrl().substring(poiImage.getPoiImageUrl().lastIndexOf("/"));
            File file = new File(path+resource+fileName);
            if(file.exists()){
                file.delete();
            }
        }
        int result = POIMapper.delete(poiId);
        if(result ==0){
            return false;
        }
        return true;
    }
    @Override
    public  boolean update(POI poi) {
        POI poi1 = POIMapper.findByPOIId(poi.getPoiId());
        if(poi1 == null){
            throw new NullException(ResultEnum.POI_NOT_EXISTS.getMessage());
        }
        int result = POIMapper.update(poi);
        if(result == 0){
            return false;
        }
        return true;
    }

    @Override
    public List<POI> find(Integer currPage, Integer pageSize,String regionId) {
        List<POI> list = POIMapper.findByRegionId(regionId);
        if(list.size()==0){
            throw new NullException(ResultEnum.POI_NOT_EXISTS.getMessage());
        }
        Integer firstIndex = (currPage-1)*pageSize;
        Integer lastIndex=currPage * pageSize;
        if(list.size()<pageSize || lastIndex>list.size()){
            lastIndex = list.size();
        }
        return list.subList(firstIndex,lastIndex);
    }
}
