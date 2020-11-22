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
import travel.vo.ResultVo;

import java.util.List;

@RestController
@RequestMapping("")
public class RegionController {
    @Autowired
    private RegionServiceImpl regionService;
    @GetMapping("/regions")
    @Cacheable(cacheNames = "regions",key = "2")
    public ResultVo getAll(){
        try{
            List<Region> regionList = regionService.findAll();
            return  ResultUtil.success(regionList);
        }catch (Exception e){
            return ResultUtil.fail(e.getMessage());
        }
    }
    @PostMapping("/admin/region/add")
    @CacheEvict(cacheNames = "regions",key = "2")
    public ResultVo addRegion(@Param("regionName")String regionName){
        ResultVo resultVo = new ResultVo();

        return resultVo;
    }
}
