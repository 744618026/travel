package travel.mapper.poi;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import travel.dao.poi.POIImage;

import java.util.List;
@Repository
@Mapper
public interface POIImageMapper {
    /**
     *
     * @param POIId 景点ID
     * @return 该景点所有图片
     */
    @Select("Select * From POI_Image Where POI_Id=#{POIId}")
    List<POIImage> findByPOIId(@Param("POIId") String POIId);
}
