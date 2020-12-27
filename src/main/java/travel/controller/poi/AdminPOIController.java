package travel.controller.poi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import travel.configuration.RedisBasePrefix;
import travel.dao.poi.POI;
import travel.dao.poi.POIImage;
import travel.dataForm.POIForm;
import travel.service.serviceImpl.poi.POIImageServiceImpl;
import travel.service.serviceImpl.poi.POIServiceImpl;
import travel.service.serviceImpl.region.RegionServiceImpl;
import travel.utils.PageVoUtil;
import travel.utils.RedisUtil;
import travel.utils.ResultUtil;
import travel.vo.poi.POIVo;
import travel.vo.ResultVo;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin/poi")
public class AdminPOIController{
    @Autowired
    private POIServiceImpl poiService;
    @Autowired
    private POIImageServiceImpl poiImageService;
    @Autowired
    private RegionServiceImpl regionService;
    @Autowired
    private RedisTemplate redisTemplate;
    private Logger LOG = LoggerFactory.getLogger(AdminPOIController.class);
    @GetMapping("/getPOI")
    public ResultVo Page(@RequestParam(value = "size",defaultValue = "15")Integer size,@RequestParam(value = "page",defaultValue = "1")Integer page,
                         @RequestParam("regionId")String regionId){
        RedisBasePrefix prefix = new RedisBasePrefix("admin".concat(":").concat("poi").concat(":").concat(regionId).concat(":").concat("pageAndSize"));
        ResultVo regionVo = (ResultVo) RedisUtil.get(prefix,page.toString(),redisTemplate);
        if(regionVo == null){
            try{
                List<POI> poiList = poiService.findByRegionId(regionId);
                List<POIVo> poiVoList = new ArrayList<>();
                Integer end = page*size>poiList.size()?poiList.size():page*size;
                for(Integer i=(page-1)*size;i<end;i++){
                    POIVo poiVo = new POIVo();
                    BeanUtils.copyProperties(poiList.get(i),poiVo);
                    poiVoList.add(poiVo);
                }
                ResultVo resultVo = ResultUtil.success( PageVoUtil.getPage(page,size,poiList,poiVoList));
                boolean result = RedisUtil.set(redisTemplate,prefix,page.toString()+size.toString(),resultVo);
                if(!result){
                    LOG.info("request：/admin/poi/getPOI缓存失败！");
                }
                LOG.info("request：/admin/poi/getPOI缓存成功！");
                return resultVo;
            }catch (Exception e){
                return ResultUtil.fail(e.getMessage());
            }
        }else{
            LOG.info("request：/admin/poi/getPOI获取缓存成功！");
            return regionVo;
        }
    }
    //添加景点
    @PostMapping("/add")
    public ResultVo poiAdd(@Valid POIForm poiForm,BindingResult bindingResult) throws IllegalStateException{
        boolean re = removeRedis(redisTemplate,poiForm.getOldRegionId());
        if(!re){
            LOG.info("request：/admin/poi/add清除redis缓存失败！");
        }
        LOG.info("request：/admin/poi/add清除缓存");
        if(bindingResult.hasErrors()){
            LOG.info("request：/admin/poi/admin表单poiForm存在错误！");
            return ResultUtil.fail(bindingResult.getFieldError().getDefaultMessage());
        }
        try{
            regionService.findByRegionId(poiForm.getOldRegionId());
            POI poi = new POI();
            BeanUtils.copyProperties(poiForm,poi);
            poi.setRegionId(poiForm.getOldRegionId());
            boolean result = poiService.insert(poi);
            if(result){
                LOG.info("request：/admin/poi/admin 添加景点成功！");
                return ResultUtil.success();
            }
            return ResultUtil.fail();
        }catch (Exception e){
            LOG.info("request：request：/admin/poi/admin 添加景点失败！reason："+e.getMessage());
            return ResultUtil.fail(e.getMessage());
        }
    }
//    //删除景点
//    @GetMapping("/delete")
//    public ResultVo delete(@RequestParam("poiId")String poiId,@RequestParam("regionId")String regionId){
//        try{
//            boolean result = poiService.delete(poiId);
//            if(result){
//                return ResultUtil.success();
//            }else{
//                return ResultUtil.fail();
//            }
//        }catch (Exception e){
//            return ResultUtil.fail(e.getMessage());
//        }
//    }
    @PostMapping("/update")
    public ResultVo poiUpdate(@Valid POIForm poiForm, BindingResult bindingResult){
        boolean re = removeRedis(redisTemplate,poiForm.getOldRegionId());
        if(!re){
            LOG.info("request：/admin/poi/update清除redis缓存失败！");
        }
        if(bindingResult.hasErrors()){
            LOG.info("request：/admin/poi/update poiForm存在错误！");
            return ResultUtil.fail(bindingResult.getFieldError().getDefaultMessage());
        }
        try{
            POI poi = new POI();
            BeanUtils.copyProperties(poiForm,poi);
            poi.setRegionId(poiForm.getOldRegionId());
            boolean result = poiService.update(poi);
            if(result){
                return ResultUtil.success();
            }else{
                return ResultUtil.fail();
            }
        }catch (Exception e){
            LOG.info("request：/admin/poi/update");
            return ResultUtil.fail(e.getMessage());
        }
    }

    //添加景点图片
    @PostMapping("/image/upload")
    public ResultVo poiImageUpload(@RequestParam("file")MultipartFile file,@RequestParam("poiId")String poiId){
        RedisBasePrefix prefix = new RedisBasePrefix("travel".concat(":").concat(":").concat("poi").concat(":").concat("images"));
        boolean re = RedisUtil.delete(prefix,poiId,redisTemplate);
        if (!re) {
            LOG.info("request:/admin/poi/image/upload cannot remove redis cache!");
        }
        LOG.info("request:/admin/poi/image/upload has removed redis cache!");
        try{
            POIImage poiImage = new POIImage();
            poiImage.setPoiId(poiId);
            boolean result = poiImageService.insert(poiImage,file);
            if(result){
                return ResultUtil.success();
            }else{
                return ResultUtil.fail();
            }
        }catch (Exception e){
            LOG.info(e.getMessage());
            return ResultUtil.fail(e.getMessage());
        }
    }
    //删除景点图片
    @GetMapping("/image/delete")
    public ResultVo poiImageDelete(@RequestParam("imageId")Integer imageId,@RequestParam("poiId")String poiId){
        try{
            boolean result = poiImageService.deleteByImageId(imageId);
            if(result){
                return ResultUtil.success();
            }else{
                return ResultUtil.fail();
            }
        }catch (Exception e){
            return ResultUtil.fail(e.getMessage());
        }
    }
    private static boolean removeRedis(RedisTemplate redisTemplate,String prefixName){
        RedisBasePrefix prefix = new RedisBasePrefix("admin".concat(":").concat("poi").concat(":").concat(prefixName).concat(":").concat("pageAndSize"));
        RedisBasePrefix prefix1 = new RedisBasePrefix("travel".concat(":").concat("poi").concat(":").concat(prefixName).concat(":").concat("pageAndSize"));
        boolean re = RedisUtil.deleteByPrefix(prefix,redisTemplate);
        boolean re1 = RedisUtil.deleteByPrefix(prefix1,redisTemplate);
        if(!re){
            return false;
        }
        if(!re1){
            return false;
        }
        return true;
    }
}
