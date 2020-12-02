package travel.controller.region;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import travel.dao.region.Region;
import travel.enums.ReturnMessageEnum;
import travel.service.serviceImpl.region.RegionServiceImpl;
import travel.utils.ResultUtil;
import travel.vo.RegionVo;
import travel.vo.ResultVo;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/region")
public class RegionController {
    @Autowired
    private RegionServiceImpl regionService;
    @GetMapping("/getRegions")
    @Cacheable(cacheNames = "regions",key = "2")
    public ResultVo getAllRegions(){
        try{
            List<Region> regionList = regionService.findAll();
            List<RegionVo> regionVoList = new ArrayList<>();
            for(Region region : regionList){
                RegionVo regionVo = new RegionVo();
                regionVo.setName(region.getRegionName());
                regionVo.setRegionId(region.getRegionId());
                regionVoList.add(regionVo);
            }
            return  ResultUtil.success(regionVoList);
        }catch (Exception e){
            return ResultUtil.fail(e.getMessage());
        }
    }
    @CacheEvict(cacheNames = "regions",key = "2")
    @PostMapping("/admin/add")
    public ResultVo addRegion(@Param("regionName")String regionName){
        Region region = new Region();
        region.setRegionName(regionName);
        boolean result = regionService.insert(region);
        if(result){
            return ResultUtil.success();
        }
        return ResultUtil.fail();
    }
}
