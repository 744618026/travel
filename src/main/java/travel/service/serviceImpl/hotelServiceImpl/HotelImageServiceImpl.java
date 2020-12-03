package travel.service.serviceImpl.hotelServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import travel.dao.hotel.Hotel;
import travel.dao.hotel.HotelImage;
import travel.mapper.hotel.HotelImageMapper;
import travel.mapper.hotel.HotelMapper;
import travel.service.hotel.HotelImageService;
import travel.service.hotel.HotelService;

import java.util.List;

@Service
public class HotelImageServiceImpl implements HotelImageService {
    @Autowired
    private HotelImageMapper hotelImageMapper;
    @Autowired
    private HotelMapper hotelMapper;
    @Override
    public List<HotelImage> findByHotelIdAndCategory(String hotelId, Integer category) {
        List<HotelImage> hotelImageList = hotelImageMapper.findByHotelIdAndCategory(hotelId,category);
        return hotelImageList;
    }
}