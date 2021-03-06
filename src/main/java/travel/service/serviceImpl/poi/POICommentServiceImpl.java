package travel.service.serviceImpl.poi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import travel.dao.poi.POIComment;
import travel.enums.ResultEnum;
import travel.exceptions.NullException;
import travel.mapper.poi.POICommentMapper;
import travel.service.poi.POICommentService;
import travel.utils.KeyUtil;

import java.util.List;
@Service
public class POICommentServiceImpl implements POICommentService {
    @Autowired
    private POICommentMapper poiCommentMapper;
    @Override
    public List<POIComment> findByPOIId(String poiId,Integer page,Integer size) {
        List<POIComment> poiCommentList = poiCommentMapper.findByPOIId(poiId);
        if(poiCommentList.size()==0){

        }
        Integer startIndex = (page-1)*size;
        Integer endIndex = page * size;
        if(endIndex>poiCommentList.size()){
            endIndex = poiCommentList.size();
        }
        return poiCommentList.subList(startIndex,endIndex);
    }

    @Override
    public POIComment findByCommentId(String commentId) {
        POIComment poiComment = poiCommentMapper.findByCommentId(commentId);
        if(poiComment == null){
            throw new NullException(ResultEnum.COMMENT_NOT_EXISTS.getMessage());
        }
        return poiComment;
    }
    @Override
    public boolean deleteComment(String userName, String commentId) {
        POIComment poiComment = poiCommentMapper.findByCommentId(commentId);
        if(poiComment==null){
            throw new NullException(ResultEnum.COMMENT_NOT_EXISTS.getMessage());
        }
        int result = poiCommentMapper.deleteComment(userName,commentId);
        if(result == 0){
            return false;
        }
        return true;
    }

    @Override
    public boolean insert(POIComment poiComment) {
        poiComment.setPoiCommentId(KeyUtil.getUniqueKey());
        int result = poiCommentMapper.insert(poiComment);
        if(result ==0){
            return false;
        }
        return true;
    }

    @Override
    public boolean check(String userName, String poiId) {
        List<POIComment> poiCommentList = poiCommentMapper.check(userName,poiId);
        if(poiCommentList.size()==0){
            return true;
        }
        return false;
    }
}
