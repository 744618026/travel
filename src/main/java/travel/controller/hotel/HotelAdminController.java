package travel.controller.hotel;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import travel.dao.hotel.Hotel;
import travel.dataForm.HotelForm;
import travel.service.hotel.HotelService;
import travel.service.serviceImpl.region.RegionServiceImpl;
import travel.utils.KeyUtil;
import travel.utils.POIHotelKeyUtil;
import travel.utils.ResultUtil;
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
    @Cacheable(cacheNames = "admin/hotel",key = "'admin/hotel/list?'+#regionId")
    @PostMapping("/list")
    public ResultVo findByRegionId(@RequestParam("regionId")String regionId, @RequestParam(value = "page",defaultValue = "1")Integer page,
                                   @RequestParam(value = "size",defaultValue = "15")Integer size){
        try {
            regionService.findByRegionId(regionId);
            List<Hotel> hotelList = hotelService.findByRegionId(regionId,page,size);
            List<HotelVo> hotelVoList = new ArrayList<>();
            for(Hotel hotel : hotelList){
                HotelVo hotelVo = new HotelVo();
                BeanUtils.copyProperties(hotel,hotelVo);
                hotelVoList.add(hotelVo);
            }
            return ResultUtil.success(hotelVoList);
        }catch (Exception e){
            return ResultUtil.fail(e.getMessage());
        }
    }
    @PostMapping("/add")
    @Caching(evict = {@CacheEvict(cacheNames = "admin/hotel",allEntries = true)})
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
}
