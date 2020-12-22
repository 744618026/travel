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
    @Select("select * from poiComment where poiId = #{poiId}")
    List<POIComment> findByPOIId(@Param("poiId") String poiId);

    /**
     *
     * @param commentId 评论Id
     * @return 评论内容
     */
    @Select("select * from poiComment where poiCommentId = #{poiCommentId}")
    POIComment findByCommentId(@Param("poiCommentId") String commentId);
    /**
     *
     * @param userName 用户名
     * @param commentId 评论Id
     * @return int
     */
    @Delete("Delete From poiComment Where userName=#{userName} and poiCommentId =#{commentId}")
    int deleteComment(@Param("userName")String userName,@Param("commentId")String commentId);

    /**
     *
     * @param poiComment 对象
     * @return int
     */
    @Insert("Insert into poiComment(poiCommentId,poiId,userName,content) values(#{poiCommentId},#{poiId},#{userName},#{content})")
    int insert(POIComment poiComment);

    @Select("Select * from poiComment where userName=#{userName} and poiId=#{poiId}")
    List<POIComment> check(@Param("userName")String userName,@Param("poiId")String poiId);
}
