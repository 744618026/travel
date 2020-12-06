package travel.controller.poi;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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
import travel.utils.ResultUtil;
import travel.vo.ResultVo;
import javax.validation.Valid;

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
    public ModelAndView poi(){
        ModelAndView mv =new ModelAndView();
        mv.setViewName("/admin/poi/list.html");
        return mv;
    }
    //添加景点
    @CacheEvict(key = "'/poi/list?'+#poiForm.oldRegionId")
    @PostMapping("/add")
    public ResultVo poiAdd(@Valid POIForm poiForm,BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResultUtil.fail(bindingResult.getFieldError().getDefaultMessage());
        }
        try{
            regionService.findByRegionId(poiForm.getRegionId());
            POI poi = new POI();
            BeanUtils.copyProperties(poiForm,poi);
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
    @CacheEvict(key = "'/poi/list?'+#regionId")
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
    @CacheEvict(key ="'/poi/list?'+#poiForm.oldRegionId")
    //更新景点信息
    @GetMapping("/update")
    public ResultVo poiUpdate(@Valid POIForm poiForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResultUtil.fail(bindingResult.getFieldError().getDefaultMessage());
        }
        try{
            POI poi = new POI();
            BeanUtils.copyProperties(poiForm,poi);
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
    @CacheEvict(key = "'/poi/images?'+#poiId")
    //添加景点图片
    @PostMapping("/image/upload")
    public ResultVo poiImageUpload(@RequestParam("file")MultipartFile file,@RequestParam("poiId")String poiId){
        ResultVo resultVo = new ResultVo();
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
    @CacheEvict(key = "'/poi/images?'+#poiId")
    //删除景点图片
    @GetMapping("/image/delete")
    public ResultVo poiImageDelete(@RequestParam("imageId")Integer imageId){
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
