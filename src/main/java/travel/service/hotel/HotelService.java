package travel.service.hotel;

import org.apache.ibatis.annotations.Update;
import travel.dao.hotel.Hotel;

import java.util.List;

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
     * @param hotel
     * @return
     */
    boolean update(Hotel hotel);

    /**
     *
     * @param regionId 地区Id
     * @return
     */
    List<Hotel> findByRegionId(String regionId);
}
