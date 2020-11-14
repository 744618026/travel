package travel.service.serviceImpl.folkways;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import travel.dao.folkways.FolkwaysImage;
import travel.mapper.folkways.FolkwaysImageMapper;
import travel.service.folkways.FolkwaysImageService;

import java.util.List;
@Service
public class FolkwaysImageServiceImpl implements FolkwaysImageService {
    @Autowired
    private FolkwaysImageMapper folkwaysImageMapper;
    @Override
    public List<FolkwaysImage> findByFolkwaysId(String folkwaysId) {
        return folkwaysImageMapper.findByFolkwaysId(folkwaysId);
    }

    @Override
    public boolean delete(Integer folkwaysImageId) {
        int result = folkwaysImageMapper.delete(folkwaysImageId);
        if(result == 0){
            return false;
        }
        return true;
    }
}
