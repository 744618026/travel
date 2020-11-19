package travel.controller.poi;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import travel.dao.poi.POI;
import travel.dao.poi.POIImage;
import travel.dataForm.POIForm;
import travel.enums.ReturnMessageEnum;
import travel.service.serviceImpl.poi.POIImageServiceImpl;
import travel.service.serviceImpl.poi.POIServiceImpl;
import travel.vo.ResultVo;
import javax.validation.Valid;

@RestController
@RequestMapping("/admin/poi")
@CacheEvict(cacheNames = "pois",key = "123")
public class AdminPOIController{
    @Autowired
    private POIServiceImpl poiService;
    @Autowired
    private POIImageServiceImpl poiImageService;
    @PostMapping("/add")
    public ResultVo poiAdd(@Valid POIForm poiForm,BindingResult bindingResult){
        ResultVo resultVo = new ResultVo();
        if(bindingResult.hasErrors()){
            resultVo.setCode(ReturnMessageEnum.FAILED.getCode());
            resultVo.setMessage(bindingResult.getFieldError().getDefaultMessage());
            return resultVo;
        }
        POI poi = new POI();
        BeanUtils.copyProperties(poiForm,poi);
        boolean result = poiService.insert(poi);
        if(result){
            resultVo.setCode(ReturnMessageEnum.SUCCESS.getCode());
            resultVo.setMessage(ReturnMessageEnum.SUCCESS.getMessage());
            return resultVo;
        }else{
            resultVo.setCode(ReturnMessageEnum.FAILED.getCode());
            resultVo.setMessage(ReturnMessageEnum.FAILED.getMessage());
            return resultVo;
        }
    }
    @GetMapping("/delete")
    public ResultVo delete(@RequestParam("poiId")String poiId){
        ResultVo resultVo = new ResultVo();
        try{
            boolean result = poiService.delete(poiId);
            if(result){
                resultVo.setCode(ReturnMessageEnum.SUCCESS.getCode());
                resultVo.setMessage(ReturnMessageEnum.SUCCESS.getMessage());
            }else{
                resultVo.setCode(ReturnMessageEnum.FAILED.getCode());
                resultVo.setMessage(ReturnMessageEnum.FAILED.getMessage());
            }
        }catch (Exception e){
            resultVo.setCode(ReturnMessageEnum.FAILED.getCode());
            resultVo.setMessage(e.getMessage());
        }
        return resultVo;
    }
    @GetMapping("/update")
    public ResultVo poiUpdate(@Valid POIForm poiForm, BindingResult bindingResult){
        ResultVo resultVo = new ResultVo();
        //TODO
        if(bindingResult.hasErrors()){
            resultVo.setCode(ReturnMessageEnum.FAILED.getCode());
            resultVo.setMessage(bindingResult.getFieldError().getDefaultMessage());
            return resultVo;
        }
        try{
            POI poi = new POI();
            BeanUtils.copyProperties(poiForm,poi);
            boolean result = poiService.update(poi);
            if(result){
                resultVo.setCode(ReturnMessageEnum.SUCCESS.getCode());
                resultVo.setMessage(ReturnMessageEnum.SUCCESS.getMessage());
            }else{
                resultVo.setCode(ReturnMessageEnum.FAILED.getCode());
                resultVo.setMessage(ReturnMessageEnum.FAILED.getMessage());
            }
        }catch (Exception e){
            resultVo.setCode(ReturnMessageEnum.FAILED.getCode());
            resultVo.setMessage(e.getMessage());
        }
        return resultVo;
    }
    @PostMapping("/image/upload")
    public ResultVo poiImageUpload(@RequestParam("file")MultipartFile file,@RequestParam("poiId")String poiId){
        ResultVo resultVo = new ResultVo();
        try{
            POIImage poiImage = new POIImage();
            poiImage.setPoiId(poiId);
            file.getOriginalFilename();
            boolean result = poiImageService.insert(poiImage,file);
            if(result){
                resultVo.setCode(ReturnMessageEnum.SUCCESS.getCode());
                resultVo.setMessage(ReturnMessageEnum.SUCCESS.getMessage());
            }else{
                resultVo.setCode(ReturnMessageEnum.FAILED.getCode());
                resultVo.setMessage(ReturnMessageEnum.FAILED.getMessage());
            }
        }catch (Exception e){
            resultVo.setCode(ReturnMessageEnum.FAILED.getCode());
            resultVo.setMessage(e.getMessage());
        }
        return resultVo;
    }
    @GetMapping("/image/delete")
    public ResultVo poiImageDelete(@RequestParam("imageId")String imageId){
        ResultVo resultVo = new ResultVo();

        return resultVo;
    }
}
