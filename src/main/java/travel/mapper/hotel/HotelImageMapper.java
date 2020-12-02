package travel.mapper.hotel;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import travel.dao.hotel.HotelImage;

import java.util.List;

@Repository
@Mapper
public interface HotelImageMapper {
    /**
     *
     * @param hotelId 酒店id
     * @param category 类目编号
     * @return
     */
    @Select("Select * from Hotel where hotelId=#{hotelId} and category=#{category}")
    List<HotelImage> findByHotelIdAndCategory(@Param("hotelId")String hotelId,@Param("category")Integer category);
}
