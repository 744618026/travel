package travel.controller.poi;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import travel.dao.poi.POI;
import travel.dao.poi.POIImage;
import travel.dataForm.POIForm;
import travel.enums.ReturnMessage;
import travel.exceptions.ExceptionHandlers;
import travel.service.serviceImpl.poi.POIImageServiceImpl;
import travel.service.serviceImpl.poi.POIServiceImpl;
import travel.vo.ResultVo;
import javax.validation.Valid;

@RestController
@RequestMapping("/admin/poi")
public class AdminPOIController{
    @Autowired
    private POIServiceImpl poiService;
    @Autowired
    private POIImageServiceImpl poiImageService;
    @PostMapping("/add")
    public ResultVo poiAdd(@Valid POIForm poiForm,BindingResult bindingResult){
        ResultVo resultVo = new ResultVo();
        if(bindingResult.hasErrors()){
            resultVo.setCode(ReturnMessage.FAILED.getCode());
            resultVo.setMessage(bindingResult.getFieldError().getDefaultMessage());
            return resultVo;
        }
        POI poi = new POI();
        BeanUtils.copyProperties(poiForm,poi);
        boolean result = poiService.insert(poi);
        if(result){
            resultVo.setCode(ReturnMessage.SUCCESS.getCode());
            resultVo.setMessage(ReturnMessage.SUCCESS.getMessage());
            return resultVo;
        }else{
            resultVo.setCode(ReturnMessage.FAILED.getCode());
            resultVo.setMessage(ReturnMessage.FAILED.getMessage());
            return resultVo;
        }
    }
    @GetMapping("/delete")
    public ResultVo delete(@RequestParam("poiId")String poiId){
        ResultVo resultVo = new ResultVo();
        try{
            boolean result = poiService.delete(poiId);
            if(result){
                resultVo.setCode(ReturnMessage.SUCCESS.getCode());
                resultVo.setMessage(ReturnMessage.SUCCESS.getMessage());
            }else{
                resultVo.setCode(ReturnMessage.FAILED.getCode());
                resultVo.setMessage(ReturnMessage.FAILED.getMessage());
            }
        }catch (Exception e){
            resultVo.setCode(ReturnMessage.FAILED.getCode());
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
            boolean result = poiImageService.insert(poiImage,file);
            if(result){
                resultVo.setCode(ReturnMessage.SUCCESS.getCode());
                resultVo.setMessage(ReturnMessage.SUCCESS.getMessage());
            }else{
                resultVo.setCode(ReturnMessage.FAILED.getCode());
                resultVo.setMessage(ReturnMessage.FAILED.getMessage());
            }
        }catch (Exception e){
            resultVo.setCode(ReturnMessage.FAILED.getCode());
            resultVo.setMessage(e.getMessage());
        }
        return resultVo;
    }
}
