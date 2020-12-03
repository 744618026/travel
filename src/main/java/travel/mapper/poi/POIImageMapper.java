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
    int deleteByImageId(@Param("imageId") Integer imageId);

    /**
     *
     * @param poiId  景点Id
     * @return
     */
    @Delete("delete from POIImage where poiId=#{poiId}")
    int deleteByPoiId(@Param("poiId")String poiId);
    /**
     *
     * @param imageId 图片Id
     * @return 该图片信息
     */
    @Select("Select * From POIImage where poiImageId=#{imageId}")
    POIImage findByImageId(@Param("imageId")Integer imageId);

    /**
     *
     * @param poiImage 图片对象
     * @return int
     */
    @Insert("Insert Into POIImage(poiId,poiImageUrl) value(#{poiId},#{poiImageUrl})")
    int insert(POIImage poiImage);
}
