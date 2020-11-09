package travel.service.poi;

import travel.dao.poi.POI;

import java.util.List;

public interface POIService {
    List<POI> findByRegionId(String regionId);
    POI findByPOIId(String POIId);
}
