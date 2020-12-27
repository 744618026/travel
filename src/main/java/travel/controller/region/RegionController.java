package travel.controller.region;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import travel.configuration.RedisBasePrefix;
import travel.dao.region.Region;
import travel.dataForm.RegionForm;
import travel.service.serviceImpl.region.RegionServiceImpl;
import travel.utils.*;
import travel.vo.RegionVo;
import travel.vo.ResultVo;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("")
public class RegionController {
    @Autowired
    private RegionServiceImpl regionService;
    @Autowired
    private RedisTemplate redisTemplate;
    private Logger LOG = LoggerFactory.getLogger(RegionController.class);
    @GetMapping("/region/getRegions")
    public ResultVo getAllRegions(){
        RedisBasePrefix prefix = new RedisBasePrefix("region");
        ResultVo resultVo = (ResultVo) RedisUtil.get(prefix,"1",redisTemplate);
        if(resultVo==null){
            try{
                List<Region> regionList = regionService.findAll();
                List<RegionVo> regionVoList = Region2RegionVoUtil.covert(regionList);
                resultVo = ResultUtil.success(regionVoList);
                boolean result = RedisUtil.set(redisTemplate,prefix,"1",resultVo);
                if(!result){
                    LOG.info("request:/region/getRegions缓存失败！");
                }
                LOG.info("request:/region/getRegions缓存成功！");
            }catch (Exception e){
                LOG.info("获取地区信息失败！reason："+e.getMessage());
                return ResultUtil.fail(e.getMessage());
            }
        }
            return resultVo;
    }
    @PostMapping("/admin/region/add")
    public ResultVo addRegion(@Param("regionName")String regionName,@Param("province")String province){
        boolean re = removeRedis(redisTemplate);
        if(!re){
            LOG.info("request：/admin/region/add 缓存清理失败！");
        }
        LOG.info("request：/admin/region/add 缓存清理成功！！");
        Region region = new Region();
        region.setRegionName(regionName);
        region.setProvince(province);
        boolean result = regionService.insert(region);
        if(result){
            LOG.info("request：/admin/region/add 添加地区成功！");
            return ResultUtil.success();
        }
        LOG.info("request：/admin/region/add 添加地区失败！");
        return ResultUtil.fail();
    }
    @GetMapping("/admin/region")
    public ResultVo regionPage(@RequestParam(value = "page",defaultValue = "1")Integer page,
                               @RequestParam(value = "size",defaultValue = "15") Integer size){
        RedisBasePrefix prefix = new RedisBasePrefix("region".concat(":").concat("pageAndSize"));
        ResultVo resultVo = (ResultVo) RedisUtil.get(prefix,page.toString(),redisTemplate);
        if(resultVo==null){
            try {
                List<Region> regionList = regionService.find(page,size);
                List<RegionVo> regionVoList = Region2RegionVoUtil.covert(regionList);
                resultVo =  ResultUtil.success(PageVoUtil.getPage(page,size,regionList,regionVoList));
                RedisUtil.set(redisTemplate,prefix,page.toString()+size.toString(),resultVo);
            }catch (Exception e){
                return ResultUtil.fail(e.getMessage());
            }
        }
        return resultVo;
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
    public ResultVo update(@Valid RegionForm regionForm, BindingResult bindingResult){
        boolean re = removeRedis(redisTemplate);
        if(!re){
            LOG.info("request：/admin/region/update 缓存清理失败！");
        }
        LOG.info("request：/admin/region/update 缓存清理成功！！");
        if(bindingResult.hasErrors()){
            LOG.info("request：/admin/region/update 提交表单存在错误！");
            return ResultUtil.fail(bindingResult.getFieldError().getDefaultMessage());
        }
        Region region = RegionFrom2RegionUtil.convert(regionForm);
        try{
            boolean result =regionService.update(region);
            if(result){
                LOG.info("request：/admin/region/update 更新成功！");
                return ResultUtil.success();
            }else{
                LOG.info("request：/admin/region/update 更新失败！");
                return ResultUtil.fail();
            }
        }catch (Exception e){
            LOG.info("request：/admin/region/update 运行异常：！"+e.getMessage());
            return ResultUtil.fail(e.getMessage());
        }
    }
    private static boolean removeRedis(RedisTemplate redisTemplate){
        RedisBasePrefix prefix = new RedisBasePrefix("region".concat(":"));
        RedisBasePrefix prefix1 = new RedisBasePrefix("region".concat(":").concat("pageAndSize"));
        boolean result = RedisUtil.deleteByPrefix(prefix,redisTemplate);
        if(!result){
            return false;
        }
        boolean result1 = RedisUtil.deleteByPrefix(prefix1,redisTemplate);
        if(!result1){
            return false;
        }
        return true;
    }
}
