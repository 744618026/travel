package travel.service.serviceImpl.poi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import travel.dao.poi.POI;
import travel.enums.ResultEnum;
import travel.exceptions.SellException;
import travel.mapper.poi.POIMapper;
import travel.service.poi.POIService;

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
            throw new SellException(ResultEnum.POI_NOT_EXISTS.getMessage());
        }
        return poi;
    }
}
