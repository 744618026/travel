package travel.controller.hotel;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import travel.configuration.RedisBasePrefix;
import travel.dao.hotel.Hotel;
import travel.dao.hotel.HotelImage;
import travel.dao.hotel.Product;
import travel.enums.HotelEnum;
import travel.service.serviceImpl.hotelServiceImpl.HotelImageServiceImpl;
import travel.service.serviceImpl.hotelServiceImpl.HotelServiceImpl;
import travel.service.serviceImpl.hotelServiceImpl.ProductServiceImpl;
import travel.service.serviceImpl.region.RegionServiceImpl;
import travel.utils.PageVoUtil;
import travel.utils.RedisUtil;
import travel.utils.ResultUtil;
import travel.vo.HotelImageVo;
import travel.vo.HotelProductVo;
import travel.vo.HotelVo;
import travel.vo.ResultVo;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/hotel")
public class HotelController {
    @Autowired
    private HotelServiceImpl hotelService;
    @Autowired
    private RegionServiceImpl regionService;
    @Autowired
    private HotelImageServiceImpl hotelImageService;
    @Autowired
    private ProductServiceImpl productService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Cacheable(cacheNames = "hotel",key = "'hotel/list?'+#regionId")
    @GetMapping("/list")
    public ResultVo findByRegionId(@RequestParam("regionId")String regionId,@RequestParam(value = "page",defaultValue = "1")Integer page,
                                   @RequestParam(value = "size",defaultValue = "15")Integer size){
        try {
            List<Hotel> hotelList = hotelService.findByRegionId(regionId,page,size);
            List<HotelVo> hotelVoList = new ArrayList<>();
            for(Hotel hotel:hotelList){
                HotelVo hotelVo = new HotelVo();
                BeanUtils.copyProperties(hotel,hotelVo);
                hotelVoList.add(hotelVo);
            }
            return ResultUtil.success(PageVoUtil.getPage(page,size,hotelList,hotelVoList));
        }catch (Exception e){
            return ResultUtil.fail(e.getMessage());
        }
    }
    @GetMapping("/images")
    @Cacheable(cacheNames = "hotel",key = "#hotelId")
    public ResultVo findHotelImage(@RequestParam("hotelId")String hotelId){
        try{
            List<HotelImage> hotelImageList = hotelImageService.findByHotelIdAndCategory(hotelId,HotelEnum.HOTEL_IMAGE.getInteger());
            List<HotelImageVo> hotelImageVoList = new ArrayList<>();
            for(HotelImage hotelImage : hotelImageList){
                HotelImageVo hotelImageVo = new HotelImageVo();
                BeanUtils.copyProperties(hotelImage,hotelImageVo);
                hotelImageVoList.add(hotelImageVo);
            }
            return ResultUtil.success(hotelImageVoList);
        }catch (Exception e){
            return ResultUtil.fail(e.getMessage());
        }
    }
    @GetMapping("/product/list")
    public ResultVo getProduct(@RequestParam("hotelId")String hotelId,@RequestParam(value = "page",defaultValue = "1")Integer page,
                               @RequestParam(value = "size",defaultValue = "15")Integer size) {
        RedisBasePrefix redisBasePrefix = new RedisBasePrefix((long) (24*60*60), hotelId);
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
                return resultVo;
            } else {
                return resultVo;
            }
        } catch (Exception e) {
            return ResultUtil.fail();
        }
    }
}
