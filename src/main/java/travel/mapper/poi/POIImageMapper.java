package travel.mapper.poi;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import travel.dao.poi.POIImage;

import java.util.List;
@Repository
@Mapper
public interface POIImageMapper {
    /**
     *
     * @param poiId 景点ID
     * @return 该景点所有图片
     */
    @Select("Select * From POIImage Where poiId=#{poiId}")
    List<POIImage> findByPOIId(@Param("poiId") String poiId);

    /**
     *
     * @param imageId 图片Id
     * @return int
     */
    @Delete("delete from POIImage where poiImageId=#{imageId}")
    int delete(@Param("imageId") Integer imageId);

    /**
     *
     * @param imageId 图片Id
     * @return 该图片信息
     */
    @Select("Select * From POIImage where poiImage_Id=#{imageId}")
    POIImage findByImageId(@Param("imageId")Integer imageId);

    /**
     *
     * @param poiImage 图片对象
     * @return int
     */
    @Insert("Insert Into POIImage(POI_Id,POI_Image_Url) value(#{POIId},#{POIImageUrl})")
    int insert(POIImage poiImage);
}
