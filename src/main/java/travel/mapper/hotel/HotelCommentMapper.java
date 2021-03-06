package travel.mapper.hotel;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import travel.dao.hotel.HotelComment;
import travel.dao.poi.POIComment;

import java.util.List;

@Repository
@Mapper
public interface HotelCommentMapper {
    /**
     *
     * @param hotelId
     * @return 该酒店所有评论list
     */
    @Select("Select * from Hotel_Comment where hotelId=#{hotelId}")
    List<HotelComment> findByHotelId(@Param("hotelId") String hotelId);

//    @Insert("Insert into poiComment(poiId,userName,content) values(#{poiId},#{userName},#{content})")
//    int insert(POIComment poiComment);
}
