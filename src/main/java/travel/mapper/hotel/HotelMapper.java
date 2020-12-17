package travel.mapper.hotel;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import travel.dao.hotel.Hotel;

import java.util.List;

@Repository
@Mapper
public interface HotelMapper {
    /**
     *
     * @param hotelId 酒店Id
     * @return 酒店信息
     */
    @Select("select * from hotel where hotelId=#{hotelId}")
    Hotel findByHotelId(@Param("hotelId") String hotelId);

    /**
     *
     * @param hotelId
     * @return int
     */
    @Delete("delete from hotel where hotelId=#{hotelId}")
    int delete(@Param("hotelId") String hotelId);

    /**
     *
     * @param hotel 对象
     * @return int
     */
    @Update("Update set hotelName=#{hotelName},hotelDescribe=#{hotelDescribe},regionId=#{regionId}")
    int update(Hotel hotel);
    /**
     *
     * @param regionId
     * @return
     */
    @Select("Select * from hotel where regionId=#{regionId}")
    List<Hotel> findByRegionId(@Param("regionId") String regionId);

    @Insert("insert into hotel(hotelId,hotelName,hotelDescribe,regionId,address) values(#{hotelId},#{hotelName},#{hotelDescribe},#{regionId},#{address})")
    int insert(Hotel hotel);
}
