package travel.controller.poi;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.security.core.parameters.P;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import travel.dao.poi.POI;
import travel.dao.poi.POIImage;
import travel.dao.region.Region;
import travel.dataForm.POIForm;
import travel.service.serviceImpl.poi.POIImageServiceImpl;
import travel.service.serviceImpl.poi.POIServiceImpl;
import travel.service.serviceImpl.region.RegionServiceImpl;
import travel.utils.POI2POIVo;
import travel.utils.ResultUtil;
import travel.vo.POIVo;
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


    @GetMapping("/list")
    public ModelAndView list(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/admin/poi/list.html");
        return modelAndView;
    }
    @GetMapping("/add")
    public ModelAndView add(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/admin/poi/add.html");
        return modelAndView;
    }
    @Cacheable(cacheNames = "admin/poi",key = "'/admin/poi/getPOI?'+#page+#regionId")
    @GetMapping("/getPOI")
    public ResultVo Page(@RequestParam(value = "size",defaultValue = "15")Integer size,@RequestParam(value = "page",defaultValue = "1")Integer page,
                         @RequestParam("regionId")String regionId){
        try{
            List<POI> poiList = poiService.find(page,size,regionId);
            List<POIVo> poiVoList = new ArrayList<>();
            for(POI poi : poiList){
                poiVoList.add(POI2POIVo.convert(poi));
            }
            return ResultUtil.success(poiVoList);
        }catch (Exception e){
            return ResultUtil.fail(e.getMessage());
        }
    }
    //添加景点
    @PostMapping("/add")
    @Caching(evict = {@CacheEvict(cacheNames ="poi",allEntries = true),
                     @CacheEvict(cacheNames = "admin/poi",allEntries = true),
                        @CacheEvict(cacheNames = "poi/totalPage",allEntries = true)})
    public ResultVo poiAdd(@Valid POIForm poiForm,BindingResult bindingResult) throws IllegalStateException{
        if(bindingResult.hasErrors()){
            return ResultUtil.fail(bindingResult.getFieldError().getDefaultMessage());
        }
        try{
            regionService.findByRegionId(poiForm.getOldRegionId());
            POI poi = new POI();
            BeanUtils.copyProperties(poiForm,poi);
            poi.setRegionId(poiForm.getOldRegionId());
            boolean result = poiService.insert(poi);
            if(result){
                return ResultUtil.success();
            }else{
                return ResultUtil.fail();
            }
        }catch (Exception e){
            return ResultUtil.fail(e.getMessage());
        }
    }
    @Caching(evict = {@CacheEvict(cacheNames ="poi",allEntries = true),
            @CacheEvict(cacheNames = "admin/poi",allEntries = true),
            @CacheEvict(cacheNames = "poi/totalPage",allEntries = true)})
    //删除景点
    @GetMapping("/delete")
    public ResultVo delete(@RequestParam("poiId")String poiId,@RequestParam("regionId")String regionId){
        try{
            boolean result = poiService.delete(poiId);
            if(result){
                return ResultUtil.success();
            }else{
                return ResultUtil.fail();
            }
        }catch (Exception e){
            return ResultUtil.fail(e.getMessage());
        }
    }
    @Caching(evict = {@CacheEvict(cacheNames ="poi",allEntries = true),
            @CacheEvict(cacheNames = "admin/poi",allEntries = true)})
    @PostMapping("/update")
    public ResultVo poiUpdate(@Valid POIForm poiForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
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
            return ResultUtil.fail(e.getMessage());
        }
    }

    //添加景点图片

    @PostMapping("/image/upload")
    @Caching(evict = {@CacheEvict(cacheNames = "poi/images",key = "#poiId",allEntries = true)})
    public ResultVo poiImageUpload(@RequestParam("file")MultipartFile file,@RequestParam("poiId")String poiId){
        try{
            POIImage poiImage = new POIImage();
            poiImage.setPoiId(poiId);
            file.getOriginalFilename();
            boolean result = poiImageService.insert(poiImage,file);
            if(result){
                return ResultUtil.success();
            }else{
                return ResultUtil.fail();
            }
        }catch (Exception e){
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
}
