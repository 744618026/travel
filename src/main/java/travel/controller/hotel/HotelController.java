package travel.controller.hotel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import travel.configuration.RedisBasePrefix;
import travel.dao.hotel.Hotel;
import travel.dao.hotel.HotelImage;
import travel.dao.hotel.Product;
import travel.enums.HotelEnum;
import travel.service.hotel.HotelService;
import travel.service.serviceImpl.hotelServiceImpl.HotelImageServiceImpl;
import travel.service.serviceImpl.hotelServiceImpl.HotelServiceImpl;
import travel.service.serviceImpl.hotelServiceImpl.ProductServiceImpl;
import travel.utils.*;
import travel.vo.hotel.HotelImageVo;
import travel.vo.hotel.HotelProductVo;
import travel.vo.hotel.HotelVo;
import travel.vo.ResultVo;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/hotel")
public class HotelController {
    @Autowired
    private HotelServiceImpl hotelService;
    @Autowired
    private HotelImageServiceImpl hotelImageService;
    @Autowired
    private ProductServiceImpl productService;
    @Autowired
    private RedisTemplate redisTemplate;

    private Logger LOG = LoggerFactory.getLogger(HotelController.class);
    @GetMapping("/list")
    public ResultVo findByRegionId(@RequestParam("regionId")String regionId,@RequestParam(value = "page",defaultValue = "1")Integer page,
                                   @RequestParam(value = "size",defaultValue = "15")Integer size){
        RedisBasePrefix prefix = new RedisBasePrefix("hotel".concat(":").concat(regionId));
        ResultVo resultVo = (ResultVo) RedisUtil.get(prefix,page.toString(),redisTemplate);
        if(resultVo==null){
            try {
                resultVo = getResultVo(regionId, page, size, hotelService);
                boolean result = RedisUtil.set(redisTemplate,prefix,page.toString()+size.toString(),resultVo);
                if(!result){
                    LOG.info("request:/poi/list缓存失败！");
                }
            }catch (Exception e){
                LOG.info("request:/poi/list出现异常！reason："+e.getMessage());
                return ResultUtil.fail(e.getMessage());
            }
        }
        return resultVo;
    }
    public static ResultVo getResultVo(String regionId,Integer page,Integer size, HotelService hotelService) {
        ResultVo resultVo;
        List<Hotel> hotelList = hotelService.findByRegionId(regionId);
        List<HotelVo> hotelVoList = new ArrayList<>();
        Integer endIndex = page*size>hotelList.size()?hotelList.size():page*size;
        for(Integer i = (page-1)*size;i<endIndex;i++){
            Hotel hotel = hotelList.get(i);
            HotelVo hotelVo = new HotelVo();
            BeanUtils.copyProperties(hotel,hotelVo);
            hotelVoList.add(hotelVo);
        }
        resultVo = ResultUtil.success(PageVoUtil.getPage(page,size,hotelList,hotelVoList));
        return resultVo;
    }
    @GetMapping("/images")
    public ResultVo findHotelImage(@RequestParam("hotelId")String hotelId){
        RedisBasePrefix prefix = new RedisBasePrefix("hotel".concat(":").concat("images"));
        ResultVo resultVo = (ResultVo) RedisUtil.get(prefix,hotelId,redisTemplate);
        if(resultVo==null){
            try{
                List<HotelImage> hotelImageList = hotelImageService.findByHotelIdAndCategory(hotelId,HotelEnum.HOTEL_IMAGE.getInteger());
                List<HotelImageVo> hotelImageVoList = new ArrayList<>();
                for(HotelImage hotelImage : hotelImageList){
                    HotelImageVo hotelImageVo = new HotelImageVo();
                    BeanUtils.copyProperties(hotelImage,hotelImageVo);
                    hotelImageVoList.add(hotelImageVo);
                }
                resultVo = ResultUtil.success(hotelImageVoList);
                boolean result = RedisUtil.set(redisTemplate,prefix,hotelId,resultVo);
                if(!result){
                    LOG.info("request:/poi/images 缓存失败！");
                }
            }catch (Exception e){
                LOG.info("request：/poi/images出现异常！reason："+e.getMessage());
                return ResultUtil.fail(e.getMessage());
            }
        }
        return resultVo;
    }
    @GetMapping("/product/list")
    public ResultVo getProduct(@RequestParam("hotelId")String hotelId,@RequestParam(value = "page",defaultValue = "1")Integer page,
                               @RequestParam(value = "size",defaultValue = "15")Integer size) {
        RedisBasePrefix redisBasePrefix = new RedisBasePrefix(hotelId);
        ResultVo resultVo = (ResultVo) RedisUtil.get(redisBasePrefix, page.toString(), redisTemplate);
        try {
            if (resultVo == null) {
                List<Product> products = productService.findByHotelId(hotelId);
                List<HotelProductVo> hotelProductVos = new ArrayList<>();
                for (Product product : products) {
                    HotelProductVo hotelProductVo = new HotelProductVo();
                    BeanUtils.copyProperties(product, hotelProductVo);
                    hotelProductVos.add(hotelProductVo);
                }
                resultVo =  ResultUtil.success(PageVoUtil.getPage(page, size, products, hotelProductVos));
                RedisUtil.set(redisTemplate,redisBasePrefix,page.toString(),resultVo);
            }
        } catch (Exception e) {
            return ResultUtil.fail();
        }
        return resultVo;
    }

}
