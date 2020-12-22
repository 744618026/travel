package travel.controller.hotel;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.parameters.P;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import travel.configuration.RedisBasePrefix;
import travel.dao.hotel.Hotel;
import travel.dao.hotel.Product;
import travel.dataForm.HotelForm;
import travel.dataForm.ProductForm;
import travel.service.hotel.HotelService;
import travel.service.serviceImpl.hotelServiceImpl.ProductServiceImpl;
import travel.service.serviceImpl.region.RegionServiceImpl;
import travel.utils.KeyUtil;
import travel.utils.POIHotelKeyUtil;
import travel.utils.RedisUtil;
import travel.utils.ResultUtil;
import travel.vo.HotelProductVo;
import travel.vo.HotelVo;
import travel.vo.ResultVo;

import javax.validation.Valid;
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
    @GetMapping("/list")
    public ModelAndView list(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/admin/hotel/list.html");
        return mv;
    }
    @GetMapping("/add")
    public ModelAndView add(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/admin/hotel/add.html");
        return mv;
    }
    @PostMapping("/list")
    public ResultVo findByRegionId(@RequestParam("regionId")String regionId, @RequestParam(value = "page",defaultValue = "1")Integer page,
                                   @RequestParam(value = "size",defaultValue = "15")Integer size){
        RedisBasePrefix prefix = new RedisBasePrefix((long) (24*60*60),"admin".concat(":").concat("hotel").concat(":").concat(regionId));
        ResultVo resultVo = (ResultVo) RedisUtil.get(prefix,page.toString(),redisTemplate);
        if(resultVo==null){
            try {
                regionService.findByRegionId(regionId);
                List<Hotel> hotelList = hotelService.findByRegionId(regionId,page,size);
                List<HotelVo> hotelVoList = new ArrayList<>();
                for(Hotel hotel : hotelList){
                    HotelVo hotelVo = new HotelVo();
                    BeanUtils.copyProperties(hotel,hotelVo);
                    hotelVoList.add(hotelVo);
                }
                resultVo = ResultUtil.success(hotelVoList);
                RedisUtil.set(redisTemplate,prefix,page.toString(),resultVo);
                return resultVo;
            }catch (Exception e){
                return ResultUtil.fail(e.getMessage());
            }
        }
        return resultVo;
    }
    @PostMapping("/add")
    @Caching(evict = {@CacheEvict(cacheNames = "admin/hotel",allEntries = true),
                        @CacheEvict(cacheNames = "hotel",allEntries = true)})
    public ResultVo add(@Valid HotelForm hotelForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            ResultUtil.fail(bindingResult.getFieldError().getDefaultMessage());
        }
        Hotel hotel = new Hotel();
        BeanUtils.copyProperties(hotelForm,hotel);
        hotel.setHotelId(POIHotelKeyUtil.getKey());
        boolean result = hotelService.insert(hotel);
        if(result){
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
}
