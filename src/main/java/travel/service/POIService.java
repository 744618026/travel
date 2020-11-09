package travel.service;

import travel.dao.POI;

import java.util.List;

public interface POIService {
    List<POI> findByRegionId(String regionId);
    POI findByPOIId(String POIId);
}
