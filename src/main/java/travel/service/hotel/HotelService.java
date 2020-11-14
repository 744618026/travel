package travel.service.hotel;

import org.apache.ibatis.annotations.Param;
import travel.dao.hotel.Hotel;

public interface HotelService {
    /**
     *
     * @param hotelId 酒店Id
     * @return 酒店信息
     */
    Hotel findByHotelId(String hotelId);
    /**
     *
     * @param hotelId
     * @return boolean
     */
    boolean delete(String hotelId);
    /**
     *
     * @param hotelId 酒店id
     * @param describe 描述
     * @return boolean
     */
    boolean updateDescribe(String describe,String hotelId);
    /**
     *
     * @param hotelName 酒店名
     * @param hotelId 酒店Id
     * @return boolean
     */
    boolean updateHotelName(String hotelName,String hotelId);
}
