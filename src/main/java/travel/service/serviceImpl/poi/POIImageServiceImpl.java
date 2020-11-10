package travel.service.serviceImpl.poi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import travel.dao.poi.POIImage;
import travel.mapper.poi.POIImageMapper;
import travel.service.poi.POIImageService;

import java.util.List;
@Service
public class POIImageServiceImpl implements POIImageService {
    @Autowired
    private POIImageMapper poiImageMapper;
    @Override
    public List<POIImage> findByPOIId(String POIId) {
        return poiImageMapper.findByPOIId(POIId);
    }
}
