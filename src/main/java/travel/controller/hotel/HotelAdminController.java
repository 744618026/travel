package travel.controller.hotel;


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

import javax.validation.Valid;
import java.io.File;

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
        RedisBasePrefix redisBasePrefix = new RedisBasePrefix(productForm.getHotelId());
        boolean re = RedisUtil.deleteByPrefix(redisBasePrefix,redisTemplate);
        if(!re){
            //TODO
            System.out.println("缓存未清除！");
        }
        if(bindingResult.hasErrors()){
            return  ResultUtil.fail(bindingResult.getFieldError().getDefaultMessage());
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
    public ResultVo getImages(@RequestParam("hotelId")String hotelId, @RequestParam("file") MultipartFile file){
        RedisBasePrefix prefix = new RedisBasePrefix("hotel".concat(":").concat("images"));
        boolean re = RedisUtil.delete(prefix,hotelId,redisTemplate);
        if(!re){
            LOG.info("request:/poi/add/images清除缓存失败！");
        }
        try {
            String path = "";
            String resource = "hotel/" + hotelId;
            File dir = new File(path + resource);
            if (!dir.exists()) {
                boolean result = dir.mkdirs();

            }
            String fileName = FileNameUtil.getNewFileName(file);
            File file1 = new File(path + resource + "/" + fileName);
            file.transferTo(file1);
            String url = "/travel/hotel/" + hotelId + "/" + fileName;
            HotelImage hotelImage = new HotelImage();
            hotelImage.setHotelId(hotelId);
            hotelImage.getCategory(HotelEnum.HOTEL_IMAGE.getInteger());
            hotelImage.setUrl(url);
            hotelImage.setImageId(KeyUtil.getUniqueKey());
            boolean result = hotelImageService.insert(hotelImage);
            if(result){
                return ResultUtil.success();
            }
            return ResultUtil.fail();
        }catch (Exception e){
            LOG.info("request:/poi/add/images 出现异常！reason："+e.getMessage());
            return ResultUtil.fail();
        }
    }
    @PostMapping("/update")
    public ResultVo update(@Valid HotelForm hotelForm,BindingResult bindingResult){
        RedisBasePrefix prefix = new RedisBasePrefix("admin".concat(":").concat("hotel").concat(":").concat(hotelForm.getRegionId()));
        RedisBasePrefix prefix1 = new RedisBasePrefix("hotel".concat(":").concat(hotelForm.getRegionId()));
        boolean re = RedisUtil.deleteByPrefix(prefix,redisTemplate);
        boolean re1 = RedisUtil.deleteByPrefix(prefix1,redisTemplate);
        if(!re&&re1){
            LOG.info("request:/admin/hotel/update清除缓存失败！");
            return ResultUtil.fail();
        }
        LOG.info("request:/admin/hotel/update清除缓存成功！");
        if(bindingResult.hasErrors()){
            return ResultUtil.fail(bindingResult.getFieldError().getDefaultMessage());
        }
        Hotel hotel = hotelService.findByHotelId(hotelForm.getHotelId());
        if(hotel==null){
            LOG.info("request：/admin/hotel/update不存在"+hotelForm.getHotelId()+"此酒店！");
            return ResultUtil.fail();
        }
        Hotel hotel1 = new Hotel();
        BeanUtils.copyProperties(hotelForm,hotel1);
        boolean result = hotelService.update(hotel1);
        if(result){
            LOG.info("request：/admin/hotel/update 更新成功！");
            return ResultUtil.success();
        }
        return ResultUtil.fail();
    }
}
