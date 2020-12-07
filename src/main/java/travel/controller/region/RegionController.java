package travel.controller.region;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import travel.dao.region.Region;
import travel.dataForm.RegionForm;
import travel.service.serviceImpl.region.RegionServiceImpl;
import travel.utils.Region2RegionVoUtil;
import travel.utils.RegionFrom2RegionUtil;
import travel.utils.ResultUtil;
import travel.vo.RegionVo;
import travel.vo.ResultVo;

import javax.validation.Valid;
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
            List<RegionVo> regionVoList = Region2RegionVoUtil.covert(regionList);
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
            List<RegionVo> regionVoList = Region2RegionVoUtil.covert(regionList);
            return ResultUtil.success(regionVoList);
        }catch (Exception e){
            return ResultUtil.fail(e.getMessage());
        }

    }
    @PostMapping("/admin/region/search")
    public ResultVo search(@RequestParam("search")String regionName){
        try{
            List<Region> regions = regionService.findByName(regionName);
            List<RegionVo> regionVoList = Region2RegionVoUtil.covert(regions);
            return ResultUtil.success(regionVoList);
        }catch (Exception e){
            return ResultUtil.fail(e.getMessage());
        }

    }
    @PostMapping("/admin/region/update")
    @CacheEvict(cacheNames = "region",key = "'/admin/region?'+#regionForm.page")
    public ResultVo update(@Valid RegionForm regionForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResultUtil.fail(bindingResult.getFieldError().getDefaultMessage());
        }
        Region region = RegionFrom2RegionUtil.convert(regionForm);
        try{
            boolean result =regionService.update(region);
            if(result){
                return ResultUtil.success();
            }else{
                return ResultUtil.fail();
            }
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
