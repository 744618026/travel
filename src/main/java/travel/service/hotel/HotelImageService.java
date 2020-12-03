package travel.service.hotel;

import travel.dao.hotel.HotelImage;

import java.util.List;

public interface HotelImageService {

    List<HotelImage> findByHotelIdAndCategory(String hotelId,Integer category);
}