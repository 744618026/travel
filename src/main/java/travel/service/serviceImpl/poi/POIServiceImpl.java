package travel.service.serviceImpl.poi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import travel.dao.poi.POI;
import travel.enums.ResultEnum;
import travel.exceptions.NullException;
import travel.mapper.poi.POIMapper;
import travel.service.poi.POIService;
import travel.utils.KeyUtils;

import java.math.BigDecimal;
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
    public POI findByPOIId(String poiId) {
        POI poi = POIMapper.findByPOIId(poiId);
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
    public  boolean delete(String poiId) {
        POI poi = POIMapper.findByPOIId(poiId);
        if(poi == null){
            throw new NullException(ResultEnum.POI_NOT_EXISTS.getMessage());
        }
        int result = POIMapper.delete(poiId);
        if(result ==0){
            return false;
        }
        return true;
    }

    @Override
    public  boolean updatePOIStock(Integer poiStock, String poiId) {
        POI poi = POIMapper.findByPOIId(poiId);
        if(poi == null){
            throw new NullException(ResultEnum.POI_NOT_EXISTS.getMessage());
        }
        int result = POIMapper.updatePOIStock(poiStock,poiId);
        if(result == 0){
            return false;
        }
        return true;
    }

    @Override
    public boolean updatePOITicketPrice(BigDecimal poiTicketPrice, String poiId) {
        POI poi = POIMapper.findByPOIId(poiId);
        if(poi == null){
            throw new NullException(ResultEnum.POI_NOT_EXISTS.getMessage());
        }
        int result = POIMapper.updatePOITicketPrice(poiTicketPrice,poiId);
        if(result == 0){
            return false;
        }
        return true;
    }
}
