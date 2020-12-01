package travel.controller.hotel;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import travel.dao.hotel.Hotel;
import travel.service.serviceImpl.hotelServiceImpl.HotelServiceImpl;
import travel.service.serviceImpl.region.RegionServiceImpl;
import travel.utils.ResultUtil;
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
    @Cacheable(cacheNames = "hotels",key = "3")
    @RequestMapping("/list")
    public ResultVo findByRegionId(@RequestParam("regionId")String regionId){
        try {
            regionService.findByRegionId(regionId);
            List<Hotel> hotelList = hotelService.findByRegionId(regionId);
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
    @GetMapping("/findOne")
    public ResultVo findByHotelId(@RequestParam("hotelId")String hotelId){
        try{
            Hotel hotel = hotelService.findByHotelId(hotelId);
            HotelVo hotelVo = new HotelVo();
            BeanUtils.copyProperties(hotel,hotelVo);
            return ResultUtil.success(hotelVo);
        }catch (Exception e){
            return ResultUtil.fail(e.getMessage());
        }
    }
}
