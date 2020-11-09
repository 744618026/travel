package travel.service.serviceImpl.poi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import travel.dao.poi.POIComment;
import travel.mapper.poi.POICommentMapper;
import travel.service.poi.POICommentService;

import java.util.List;
@Service
public class POICommentServiceImpl implements POICommentService {
    @Autowired
    private POICommentMapper poiCommentMapper;
    @Override
    public List<POIComment> findByPOIId(String POIId) {
        return poiCommentMapper.findByPOIId(POIId);
    }
}
