package travel.mapper.hotel;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import travel.dao.hotel.Hotel;

@Repository
@Mapper
public interface HotelMapper {
    /**
     *
     * @param hotelId 酒店Id
     * @return 酒店信息
     */
    @Select("select * from Hotel where Hotel_Id=#{hotelId}")
    Hotel findByHotelId(@Param("hotelId") String hotelId);

    /**
     *
     * @param hotelId
     * @return int
     */
    @Delete("delete from Hotel where Hotel_Id=#{hotelId}")
    int delete(@Param("hotelId") String hotelId);

    /**
     *
     * @param hotelId 酒店id
     * @param describe 描述
     * @return int
     */
    @Update("update Hotel set Hotel_Describe=#{describe} where Hotel_Id=#{hotelId}")
    int updateDescribe(@Param("describe") String describe,@Param("hotelId") String hotelId);

    /**
     *
     * @param hotelName 酒店名
     * @param hotelId 酒店Id
     * @return
     */
    @Update("update Hotel set Hotel_Name=#{hotelName} where Hotel_Id=#{hotelId}")
    int updateHotelName(@Param("hotelName") String hotelName,@Param("hotelId") String hotelId);
}
