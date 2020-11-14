package travel.controller.poi;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import travel.dao.poi.POI;
import travel.dao.poi.POIImage;
import travel.dataForm.POIForm;
import travel.enums.ResultEnum;
import travel.enums.ReturnMessage;
import travel.service.serviceImpl.poi.POIServiceImpl;
import travel.vo.ResultVo;

import javax.validation.Valid;
import java.net.URL;

@RestController
@RequestMapping("/admin/poi")
public class AdminPOIController {
    @Autowired
    private POIServiceImpl poiService;
    @PostMapping("/add")
    public ResultVo poiAdd(@Valid POIForm poiForm, BindingResult bindingResult){
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
    @PostMapping("/delete")
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
        if(file.isEmpty()){
            resultVo.setCode(ReturnMessage.FAILED.getCode());
            resultVo.setMessage(ResultEnum.UPLOAD_FILE_NULL.getMessage());
        }
        try{
            POIImage poiImage = new POIImage();
            poiImage.setPoiId(poiId);
            String path = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        }catch (Exception e){
            resultVo.setCode(ReturnMessage.FAILED.getCode());
            resultVo.setMessage(e.getMessage());
        }
        return resultVo;
    }
}
