package travel.service.region;

import travel.dao.region.Region;

import java.util.List;

public interface RegionService {

    List<Region> findAll();

    boolean insert(Region region);

    Region findByRegionId(String regionId);

    List<Region> find(Integer currPage,Integer pageSize);

    List<Region> findByName(String regionName);

    boolean update(Region region);
}
