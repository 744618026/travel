package travel.controller.region;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import travel.dao.region.Region;
import travel.service.serviceImpl.region.RegionServiceImpl;
import travel.utils.ResultUtil;
import travel.vo.RegionVo;
import travel.vo.ResultVo;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("")
public class RegionController {
    @Autowired
    private RegionServiceImpl regionService;
    @GetMapping("/region/getRegions")
    @Cacheable(cacheNames = "region",key = "1")
    public ResultVo getAllRegions(){
        try{
            List<Region> regionList = regionService.findAll();
            List<RegionVo> regionVoList = new ArrayList<>();
            for(Region region : regionList){
                RegionVo regionVo = new RegionVo();
                BeanUtils.copyProperties(region,regionVo);
                regionVoList.add(regionVo);
            }
            return  ResultUtil.success(regionVoList);
        }catch (Exception e){
            return ResultUtil.fail(e.getMessage());
        }
    }
    @PostMapping("/admin/region/add")
    @Caching(evict = {@CacheEvict(cacheNames = "region",key = "1"),
                      @CacheEvict(cacheNames = "region",key = "'/admin/region?'+#page")})
    public ResultVo addRegion(@Param("regionName")String regionName,@Param("province")String province,@RequestParam("page")Integer page){
        Region region = new Region();
        region.setRegionName(regionName);
        region.setProvince(province);
        boolean result = regionService.insert(region);
        if(result){
            return ResultUtil.success();
        }
        return ResultUtil.fail();
    }
    @GetMapping("/admin/region")
    @Cacheable(cacheNames = "region",key = "'/admin/region?'+#page")
    public ResultVo regionPage(@RequestParam(value = "page",defaultValue = "1")Integer page,
                               @RequestParam(value = "size",defaultValue = "15") Integer size){
        try {
            List<Region> regionList = regionService.find(page,size);
            List<RegionVo> regionVoList = new ArrayList<>();
            for(Region region : regionList){
                RegionVo regionVo = new RegionVo();
                BeanUtils.copyProperties(region,regionVo);
                regionVoList.add(regionVo);
            }
            return ResultUtil.success(regionVoList);
        }catch (Exception e){
            return ResultUtil.fail(e.getMessage());
        }

    }
    @GetMapping("/admin/region/list")
    public ModelAndView region(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/admin/region/list.html");
        return modelAndView;
    }
}
