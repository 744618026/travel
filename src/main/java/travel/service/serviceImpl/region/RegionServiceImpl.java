package travel.service.serviceImpl.region;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import travel.dao.region.Region;
import travel.enums.ResultEnum;
import travel.exceptions.NullException;
import travel.mapper.region.RegionMapper;
import travel.service.region.RegionService;

import java.util.List;
@Service
public class RegionServiceImpl implements RegionService {
    @Autowired
    private RegionMapper regionMapper;
    @Override
    public List<Region> findAll() {
        return regionMapper.findAll();
    }
    @Override
    public boolean insert(Region region) {
        int result = regionMapper.insert(region);
        if(result!=0){
            return true;
        }
        return false;
    }

    @Override
    public Region findByRegionId(String regionId) {
        Region region = regionMapper.findByRegionId(regionId);
        if(region == null){
            throw new NullException(ResultEnum.REGION_NOT_EXISTS.getMessage());
        }
        return region;
    }

}
