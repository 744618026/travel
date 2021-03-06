package travel.service.serviceImpl.region;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import travel.dao.region.Region;
import travel.enums.ResultEnum;
import travel.exceptions.NullException;
import travel.mapper.region.RegionMapper;
import travel.service.region.RegionService;
import travel.utils.RegionKeyUtil;

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
        region.setRegionId(RegionKeyUtil.getKey());
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

    @Override
    public List<Region> find(Integer currPage, Integer pageSize) {
        List<Region> list = regionMapper.findAll();
        Integer firstIndex = (currPage-1)*pageSize;
        Integer lastIndex=currPage * pageSize;
        if(list.size()<pageSize || lastIndex>list.size()){
            lastIndex = list.size();
        }
        return list.subList(firstIndex,lastIndex);
    }

    @Override
    public List<Region> findByName(String regionName) {
        return regionMapper.findByName(regionName);
    }

    @Override
    public boolean update(Region region) {
        Region region1 = regionMapper.findByRegionId(region.getRegionId());
        if(region1 == null){
            throw new NullException(ResultEnum.REGION_NOT_EXISTS.getMessage());
        }
        int result = regionMapper.update(region);
        if(result>0){
            return true;
        }
        return false;
    }

}
