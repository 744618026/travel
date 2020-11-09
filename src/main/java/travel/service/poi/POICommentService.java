package travel.service.poi;

import travel.dao.poi.POIComment;

import java.util.List;

public interface POICommentService {
    List<POIComment> findByPOIId(String POIId);
}
