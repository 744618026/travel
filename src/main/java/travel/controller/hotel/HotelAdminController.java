package travel.controller.hotel;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import travel.configuration.RedisBasePrefix;
import travel.dao.hotel.Hotel;
import travel.dao.hotel.HotelImage;
import travel.dao.hotel.Product;
import travel.dataForm.HotelForm;
import travel.dataForm.ProductForm;
import travel.enums.HotelEnum;
import travel.service.hotel.HotelImageService;
import travel.service.hotel.HotelService;
import travel.service.serviceImpl.hotelServiceImpl.ProductServiceImpl;
import travel.service.serviceImpl.region.RegionServiceImpl;
import travel.utils.*;
import travel.vo.ResultVo;
import travel.vo.hotel.HotelProductVo;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin/hotel")
public class HotelAdminController {
    @Autowired
    private RegionServiceImpl regionService;
    @Autowired
    private HotelService hotelService;
    @Autowired
    private ProductServiceImpl productService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private HotelImageService hotelImageService;
    private Logger LOG = LoggerFactory.getLogger(HotelAdminController.class);
    @PostMapping("/list")
    public ResultVo findByRegionId(@RequestParam("regionId")String regionId, @RequestParam(value = "page",defaultValue = "1")Integer page,
                                   @RequestParam(value = "size",defaultValue = "15")Integer size){
        RedisBasePrefix prefix = new RedisBasePrefix("admin".concat(":").concat("hotel").concat(":").concat(regionId));
        ResultVo resultVo = (ResultVo) RedisUtil.get(prefix,page.toString(),redisTemplate);
        if(resultVo==null){
            try {
                regionService.findByRegionId(regionId);
                resultVo = HotelController.getResultVo(regionId, page, size, hotelService);
                RedisUtil.set(redisTemplate,prefix,page.toString()+size.toString(),resultVo);
                return resultVo;
            }catch (Exception e){
                return ResultUtil.fail(e.getMessage());
            }
        }
        return resultVo;
    }
    @PostMapping("/add")
    public ResultVo add(@Valid HotelForm hotelForm, BindingResult bindingResult){
        RedisBasePrefix prefix = new RedisBasePrefix("admin".concat(":").concat("hotel").concat(":").concat(hotelForm.getRegionId()));
        RedisBasePrefix prefix1 = new RedisBasePrefix("hotel".concat(":").concat(hotelForm.getRegionId()));
        boolean re = RedisUtil.deleteByPrefix(prefix,redisTemplate);
        boolean re1 = RedisUtil.deleteByPrefix(prefix1,redisTemplate);
        if(!re&&re1){
            LOG.info("request:/admin/hotel/add清除缓存失败！");
        }
        if(bindingResult.hasErrors()){
            LOG.info("request:/admin/hotel/add提交Form存在错误！");
            ResultUtil.fail(bindingResult.getFieldError().getDefaultMessage());
        }
        Hotel hotel = new Hotel();
        BeanUtils.copyProperties(hotelForm,hotel);
        hotel.setHotelId(POIHotelKeyUtil.getKey());
        boolean result = hotelService.insert(hotel);
        if(result){
            LOG.info("request:/admin/hotel/add添加酒店成功！");
            return ResultUtil.success();
        }
        return ResultUtil.fail();
    }
    @PostMapping("/product/add")
    public ResultVo add(@Valid ProductForm productForm,BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return  ResultUtil.fail(bindingResult.getFieldError().getDefaultMessage());
        }
        RedisBasePrefix prefix = new RedisBasePrefix("hotel".concat(":").concat("product"));
        boolean re = RedisUtil.delete(prefix,productForm.getHotelId(),redisTemplate);
        if(!re){
            LOG.info("request:/admin/hotel/product/add remove cache failed");
        }
        Product product = new Product();
        BeanUtils.copyProperties(productForm,product);
        product.setProductId(KeyUtil.getUniqueKey());
        boolean result = productService.insert(product);
        if(result){
            return ResultUtil.success();
        }
        return ResultUtil.fail();
    }
    @PostMapping("/add/image")
    public ResultVo addImages(@RequestParam("hotelId")String hotelId, @RequestParam("file") MultipartFile file,HttpServletRequest request){
        if(file==null){
            LOG.info("上传图片为空！");
            return ResultUtil.fail();
        }
        RedisBasePrefix prefix = new RedisBasePrefix("hotel".concat(":").concat("images"));
        boolean re = RedisUtil.delete(prefix,hotelId,redisTemplate);
        if(!re){
            LOG.info("request:/poi/add/images removes cache failed");
            return ResultUtil.fail();
        }
        try {
            String resource = "/hotel/" + hotelId;
            String fileName = FileNameUtil.getNewFileName(file);
            boolean r = FileUploadUtil.upload(request,file,resource,fileName);
            if(!r){
                return ResultUtil.fail();
            }
            String url = "/travel/hotel/" + hotelId + "/" + fileName;
            HotelImage hotelImage = new HotelImage();
            hotelImage.setHotelId(hotelId);
            hotelImage.setCategory(HotelEnum.HOTEL_IMAGE.getInteger());
            hotelImage.setUrl(url);
            boolean result = hotelImageService.insert(hotelImage);
            if(result){
                return ResultUtil.success();
            }
        }catch (Exception e){
            LOG.info("request:/poi/add/images exceptions occurred！reason："+e.getMessage());
        }
        return ResultUtil.fail();
    }
    @PostMapping("/image/delete")
    public ResultVo deleteImage(@RequestParam("hotelId")String hotelId, @RequestParam("imageId")Integer imageId, HttpServletRequest request){
        RedisBasePrefix prefix = new RedisBasePrefix("hotel".concat(":").concat("images"));
        boolean re = RedisUtil.delete(prefix,hotelId,redisTemplate);
        if(!re){
            LOG.info("request:/admin/hotel/product/delete remove cache failed");
        }
        try {
            String path = request.getServletContext().getRealPath("");
            HotelImage hotelImage = hotelImageService.findByImageId(imageId);
            if(hotelImage==null){
                LOG.info("request:/admin/hotel/product/delete this image not exists!");
                return ResultUtil.fail();
            }
            String url = hotelImage.getUrl().replace("/travel/","");
            File file = new File(path+url);
            if(file.exists()) {
                boolean r = file.delete();
                if (!r) {
                    return ResultUtil.fail();
                }
            }
            boolean ru = hotelImageService.deleteByImageId(imageId);
            if (ru) {
                return ResultUtil.success();
            }
            return ResultUtil.fail();
        }catch (Exception e) {
            LOG.info(e.getMessage());
            return ResultUtil.fail("出现异常！");
        }
    }
    @PostMapping("/update")
    public ResultVo update(@Valid HotelForm hotelForm,BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResultUtil.fail(bindingResult.getFieldError().getDefaultMessage());
        }
        RedisBasePrefix prefix = new RedisBasePrefix("admin".concat(":").concat("hotel").concat(":").concat(hotelForm.getRegionId()));
        RedisBasePrefix prefix1 = new RedisBasePrefix("hotel".concat(":").concat(hotelForm.getRegionId()));
        boolean re = RedisUtil.deleteByPrefix(prefix,redisTemplate);
        boolean re1 = RedisUtil.deleteByPrefix(prefix1,redisTemplate);
        if(!re&&re1){
            LOG.info("request:/admin/hotel/update removes cache failed");
            return ResultUtil.fail();
        }
        Hotel hotel = hotelService.findByHotelId(hotelForm.getHotelId());
        if(hotel==null){
            LOG.info("request：/admin/hotel/update hotel "+hotelForm.getHotelId()+" not exists");
            return ResultUtil.fail();
        }
        Hotel hotel1 = new Hotel();
        BeanUtils.copyProperties(hotelForm,hotel1);
        boolean result = hotelService.update(hotel1);
        if(result){
            LOG.info("request：/admin/hotel/update update hotel info successfully！");
            return ResultUtil.success();
        }
        return ResultUtil.fail();
    }
    @GetMapping("/getProduct")
    public ResultVo getProduct(@RequestParam("hotelId")String hotelId,@RequestParam(value = "page",defaultValue = "1")Integer page,
                               @RequestParam(value = "size",defaultValue = "size")Integer size){
        RedisBasePrefix prefix = new RedisBasePrefix("hotel".concat(":").concat("product").concat(":").concat(hotelId));
        ResultVo resultVo = (ResultVo) RedisUtil.get(prefix,page.toString()+size.toString(),redisTemplate);
        if(resultVo==null){
            List<Product>  products = productService.findByHotelId(hotelId);
            List<HotelProductVo> hotelProductVos = new ArrayList<>();
            Integer endIndex = page*size>products.size()?products.size():page*size;
            for(Integer i=(page-1)*size;i<endIndex;i++){
                HotelProductVo hotelProductVo = new HotelProductVo();
                BeanUtils.copyProperties(products.get(i),hotelProductVo);
                hotelProductVos.add(hotelProductVo);
            }
            resultVo = ResultUtil.success(PageVoUtil.getPage(page,size,products,hotelProductVos));
            boolean result = RedisUtil.set(redisTemplate,prefix,page.toString()+size.toString(),resultVo);
            if(!result){
                LOG.info("request:/admin/hotel/getProduct save cache failed");
            }
        }
        return resultVo;
    }
    @PostMapping("/product/update")
    public ResultVo update(@Valid ProductForm hotelProductFrom,BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResultUtil.fail(bindingResult.getFieldError().getDefaultMessage());
        }
        RedisBasePrefix prefix = new RedisBasePrefix("hotel".concat(":").concat("product").concat(":").concat(hotelProductFrom.getHotelId()));
        boolean re = RedisUtil.deleteByPrefix(prefix,redisTemplate);
        if(!re){
            LOG.info("request:/admin/hotel/product/update removes cache failed");
            return ResultUtil.fail();
        }
        Product product = new Product();
        BeanUtils.copyProperties(hotelProductFrom,product);
        boolean result = productService.update(product);
        if(result){
            return ResultUtil.success();
        }
        return ResultUtil.fail();
    }
    @PostMapping("/product/addImage")
    public ResultVo addImage(@RequestParam("productId")String productId,@RequestParam("hotelId")String hotelId,@RequestParam("file")MultipartFile file,
                                HttpServletRequest request){
        if(file==null){
            LOG.info("上传图片为空！");
            return ResultUtil.fail();
        }
        HotelImage hotelImage = new HotelImage();
        hotelImage.setHotelId(hotelId);
        String resource = "/hotel/" + hotelId + "/product/" + productId;
        String fileName = FileNameUtil.getNewFileName(file);
        try {
            boolean r = FileUploadUtil.upload(request,file,resource,fileName);
            if(!r){
                return ResultUtil.fail();
            }
            String url = "/travel/hotel/" + hotelId + "/product/"+ productId + fileName;
            hotelImage.setHotelId(hotelId);
            hotelImage.setProductId(productId);
            hotelImage.setCategory(HotelEnum.HOTEL_IMAGE.getInteger());
            hotelImage.setUrl(url);
            boolean result = hotelImageService.insert(hotelImage);
            if(result){
                return ResultUtil.success();
            }
        }catch (Exception e){
            LOG.info("request:/admin/hotel/product/addImage occurred exceptions");
        }
        return ResultUtil.fail();
    }
}
