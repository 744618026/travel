package travel.service.region;

import travel.dao.region.Region;

import java.util.List;

public interface RegionService {

    List<Region> findAll();

    boolean insert(Region region);
}
