package travel.service.poi;

import travel.dao.poi.POIComment;

import java.util.List;

public interface POICommentService {
    /**
     *
     * @param POIId 景点ID
     * @return 返回该景点所有评论
     */
    List<POIComment> findByPOIId(String POIId);
}
