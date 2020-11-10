package travel.service.serviceImpl.poi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import travel.dao.poi.POI;
import travel.enums.ResultEnum;
import travel.exceptions.NullException;
import travel.mapper.poi.POIMapper;
import travel.service.poi.POIService;
import travel.utils.KeyUtils;

import java.util.List;

@Service
public class POIServiceImpl implements POIService {
    @Autowired
    private POIMapper POIMapper;
    @Override
    public List<POI> findByRegionId(String regionId) {
        return POIMapper.findByRegionId(regionId);
    }

    @Override
    public POI findByPOIId(String POIId) {
        POI poi = POIMapper.findByPOIId(POIId);
        if(poi ==null){
            throw new NullException(ResultEnum.POI_NOT_EXISTS.getMessage());
        }
        return poi;
    }

    @Override
    public  boolean insert(POI poi) {
        poi.setPIOId(KeyUtils.getUniqueKey());
        int result = POIMapper.insert(poi);
        if(result ==0){
            return false;
        }
        return true;
    }

    @Override
    public  boolean delete(String POIId) {
        int result = POIMapper.delete(POIId);
        if(result ==0){
            return false;
        }
        return true;
    }

    @Override
    public  boolean updatePOIStock(Integer POIStock, String POIId) {
        POI poi = POIMapper.findByPOIId(POIId);
        if(poi == null){
            throw new NullException(ResultEnum.POI_NOT_EXISTS.getMessage());
        }
        int result = POIMapper.updatePOIStock(POIStock,POIId);
        if(result == 0){
            return false;
        }
        return true;
    }
}
