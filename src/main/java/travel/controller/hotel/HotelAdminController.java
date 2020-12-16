package travel.controller.hotel;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import travel.dao.hotel.Hotel;
import travel.service.hotel.HotelService;
import travel.service.serviceImpl.region.RegionServiceImpl;
import travel.utils.ResultUtil;
import travel.vo.HotelVo;
import travel.vo.ResultVo;

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
    @Cacheable(key = "'admin/hotel/list?'+#regionId")
    @GetMapping("/list")
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
}
