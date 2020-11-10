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
    @Select("Select * From POI_Image Where POI_Id=#{POIId}")
    List<POIImage> findByPOIId(@Param("POIId") String poiId);

    /**
     *
     * @param imageId 图片Id
     * @return int
     */
    @Delete("delete from POI_Image where POI_Image_Id=#{ImageId}")
    int delete(@Param("ImageId") Integer imageId);

    /**
     *
     * @param imageId 图片Id
     * @return 该图片信息
     */
    @Select("Select * From POI_Image where POI_Image_Id=#{ImageId}")
    POIImage findByImageId(@Param("ImageId")Integer imageId);

    /**
     *
     * @param poiImage 图片对象
     * @return int
     */
    @Insert("Insert Into POI_Image(POI_Id,POI_Image_Url) value(#{POIId},#{POIImageUrl})")
    int insert(POIImage poiImage);
}
