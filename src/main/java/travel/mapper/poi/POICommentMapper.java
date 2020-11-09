package travel.mapper.poi;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import travel.dao.poi.POIComment;

import java.util.List;

@Repository
@Mapper
public interface POICommentMapper {
    /**
     *
     * @param POIId 景点ID
     * @return 该景点所有的评论
     */
    @Select("select * from POI_Comment where POI_Id = #{POIId}")
    List<POIComment> findByPOIId(@Param("POIId") String POIId);
}
