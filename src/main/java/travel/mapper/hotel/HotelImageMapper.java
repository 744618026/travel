package travel.mapper.hotel;

import org.apache.ibatis.annotations.Insert;
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
    @Select("Select * from hotelImage where hotelId=#{hotelId} and category=#{category}")
    List<HotelImage> findByHotelIdAndCategory(@Param("hotelId")String hotelId,@Param("category")Integer category);
    @Insert("Insert into hotelImage(imageId,category,url,hotelId,productId) values(" +
            "#{imageId},#{category},#{url},#{hotelId},#{productId})")
    int insert(HotelImage hotelImage);
    @Select("Select * from hotelImage where hotelId=#{hotelId} and productId=#{productId}")
    List<HotelImage> findByHotelIdAndProductId(@Param("hotelId")String hotelId,@Param("productId")String productId);
}
