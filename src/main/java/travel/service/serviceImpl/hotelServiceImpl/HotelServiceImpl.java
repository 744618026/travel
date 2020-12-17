package travel.service.serviceImpl.hotelServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import travel.dao.hotel.Hotel;
import travel.enums.ResultEnum;
import travel.exceptions.NullException;
import travel.mapper.hotel.HotelMapper;
import travel.service.hotel.HotelService;

import java.util.List;

@Service
public class HotelServiceImpl implements HotelService {
    @Autowired
    private HotelMapper hotelMapper;
    @Override
    public Hotel findByHotelId(String hotelId) {
        Hotel hotel = hotelMapper.findByHotelId(hotelId);
        if(hotel == null){
            throw new NullException(ResultEnum.HOTEL_NOT_EXISTS.getMessage());
        }
        return hotel;
    }

    @Override
    public boolean delete(String hotelId) {
        int result = hotelMapper.delete(hotelId);
        if(result == 0){
            return false;
        }
        return true;
    }

    @Override
    public boolean update(Hotel hotel) {
        Hotel hotel1 = hotelMapper.findByHotelId(hotel.getHotelId());
        if(hotel1 == null){
            throw new NullException(ResultEnum.HOTEL_NOT_EXISTS.getMessage());
        }
        int result = hotelMapper.update(hotel);
        if(result>0){
            return true;
        }
        return false;
    }
    @Override
    public List<Hotel> findByRegionId(String regionId,Integer page,Integer size) {
        List<Hotel> hotelList = hotelMapper.findByRegionId(regionId);
        Integer startIndex = (page-1)*size;
        Integer endIndex = page * size;
        if(endIndex>hotelList.size()){
            endIndex = hotelList.size();
        }
        return hotelList.subList(startIndex,endIndex);
    }

    @Override
    public boolean insert(Hotel hotel) {
        int result = hotelMapper.insert(hotel);
        if(result>0){
            return true;
        }
        return false;
    }
}
