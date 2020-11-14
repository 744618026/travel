package travel.mapper.folkways;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import travel.dao.folkways.FolkwaysImage;

import java.util.List;

@Repository
@Mapper
public interface FolkwaysImageMapper {
    /**
     *
     * @param folkwaysId 民俗Id
     * @return 该民俗所有图片
     */
    @Select("select * from Folkways_Image where Folkways_Id=#{FolkwaysId}")
    List<FolkwaysImage> findByFolkwaysId(@Param("FolkwaysId") String folkwaysId);

    /**
     *
     * @param folkwaysImageId
     * @return
     */
    @Delete("delete from Folkways_Image_Id where Folkways_Image_Id=#{folkwaysImageId}")
    int delete(@Param("folkwaysImageId") Integer folkwaysImageId);
}
