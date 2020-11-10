package travel.mapper.poi;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import travel.dao.poi.POIComment;

import java.util.List;

@Repository
@Mapper
public interface POICommentMapper {
    /**
     *
     * @param poiId 景点ID
     * @return 该景点所有的评论
     */
    @Select("select * from POI_Comment where POI_Id = #{POIId}")
    List<POIComment> findByPOIId(@Param("POIId") String poiId);

    /**
     *
     * @param commentId 评论Id
     * @return 评论内容
     */
    @Select("select * from POI_Comment where POI_Comment_Id = #{POICommentId}")
    POIComment findByCommentId(@Param("POICommentId") String commentId);
    /**
     *
     * @param userName 用户名
     * @param commentId 评论Id
     * @return int
     */
    @Delete("Delete From POI_Comment Where User_Name=#{UserName} and POI_Comment_Id =#{CommentId}")
    int deleteComment(@Param("UserName")String userName,@Param("CommentId")String commentId);

    /**
     *
     * @param poiComment 对象
     * @return int
     */
    @Insert("Insert into POI_Comment(POI_Comment_Id,POI_Id,UserName,Content) values(#{POICommentId},#{POIId},#{username},#{content})")
    int insert(POIComment poiComment);
}
