package travel.controller.hotel;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import travel.dao.hotel.Hotel;
import travel.dao.hotel.HotelImage;
import travel.enums.HotelEnum;
import travel.service.serviceImpl.hotelServiceImpl.HotelImageServiceImpl;
import travel.service.serviceImpl.hotelServiceImpl.HotelServiceImpl;
import travel.service.serviceImpl.region.RegionServiceImpl;
import travel.utils.ResultUtil;
import travel.vo.HotelImageVo;
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
    @Cacheable(key = "'hotel/list?'+#regionId")
    @RequestMapping("/list")
    public ResultVo findByRegionId(@RequestParam("regionId")String regionId){
        try {
            regionService.findByRegionId(regionId);
            List<Hotel> hotelList = hotelService.findByRegionId(regionId);
            List<HotelVo> hotelVoList = new ArrayList<>();
            for(Hotel hotel : hotelList){
                HotelVo hotelVo = new HotelVo();
                BeanUtils.copyProperties(hotel,hotelVo);
                List<HotelImage> hotelImageList = hotelImageService.findByHotelIdAndCategory(hotel.getHotelId(), HotelEnum.HOTEL_IMAGE.getInteger());
                List<HotelImageVo> hotelImageVoList = new ArrayList<>();
                for(HotelImage hotelImage : hotelImageList){
                    HotelImageVo hotelImageVo = new HotelImageVo();
                    BeanUtils.copyProperties(hotelImage,hotelImageVo);
                    hotelImageVoList.add(hotelImageVo);
                }
                hotelVo.setHotelImageVoList(hotelImageVoList);
                hotelVoList.add(hotelVo);
            }
            return ResultUtil.success(hotelVoList);
        }catch (Exception e){
            return ResultUtil.fail(e.getMessage());
        }
    }
}
