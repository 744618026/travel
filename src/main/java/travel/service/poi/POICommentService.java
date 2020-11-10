package travel.service.poi;

import org.apache.ibatis.annotations.Param;
import travel.dao.poi.POIComment;

import java.util.List;

public interface POICommentService {
    /**
     *
     * @param poiId 景点ID
     * @return 返回该景点所有评论
     */
    List<POIComment> findByPOIId(String poiId);
    /**
     *
     * @param commentId 评论Id
     * @return 评论内容
     */
    POIComment findByCommentId(String commentId);
    /**
     *
     * @param userName 用户名
     * @param commentId 评论Id
     * @return boolean
     */
    boolean deleteComment(String userName,String commentId);

    /**
     *
     * @param poiComment 对象
     * @return boolean
     */
    boolean insert(POIComment poiComment);
}
