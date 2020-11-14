package travel.service.serviceImpl.hotelServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import travel.dao.hotel.Hotel;
import travel.enums.ResultEnum;
import travel.exceptions.NullException;
import travel.mapper.hotel.HotelMapper;
import travel.service.hotel.HotelService;
@Service
public class HotelServiceImpl implements HotelService {
    @Autowired
    private HotelMapper hotelMapper;
    @Override
    public Hotel findByHotelId(String hotelId) {
        return hotelMapper.findByHotelId(hotelId);
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
    public boolean updateDescribe(String describe, String hotelId) {
        Hotel hotel = hotelMapper.findByHotelId(hotelId);
        if(hotel == null){
            throw new NullException(ResultEnum.HOTEL_NOT_EXISTS.getMessage());
        }
        int result = hotelMapper.updateDescribe(describe,hotelId);
        if(result == 0){
            return false;
        }
        return true;
    }

    @Override
    public boolean updateHotelName(String hotelName, String hotelId) {
        Hotel hotel = hotelMapper.findByHotelId(hotelId);
        if(hotel == null){
            throw new NullException(ResultEnum.HOTEL_NOT_EXISTS.getMessage());
        }
        int result = hotelMapper.updateHotelName(hotelName, hotelId);
        if(result == 0){
            return false;
        }
        return true;
    }
}
