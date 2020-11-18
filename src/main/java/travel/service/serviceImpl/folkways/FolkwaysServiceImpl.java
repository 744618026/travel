package travel.service.serviceImpl.folkways;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import travel.dao.folkways.Folkways;
import travel.enums.ResultEnum;
import travel.exceptions.NullException;
import travel.mapper.folkways.FolkwaysMapper;
import travel.service.folkways.FolkwaysService;
import travel.utils.KeyUtil;

import java.util.List;

@Service
public class FolkwaysServiceImpl implements FolkwaysService {
    @Autowired
    private FolkwaysMapper folkwaysMapper;
    @Override
    public List<Folkways> findByRegionId(String regionId) {
        return folkwaysMapper.findByRegionId(regionId);
    }
    @Override
    public Folkways findByFolkwaysId(String folkwaysId) {
        Folkways folkways = folkwaysMapper.findByFolkwaysId(folkwaysId);
        if(folkways == null){
            throw new NullException(ResultEnum.FOLKWAYS_NOT_EXISTS.getMessage());
        }
        return folkways;
    }
    @Override
    public boolean insert(Folkways folkways) {
        folkways.setFolkwaysId(KeyUtil.getUniqueKey());
        int result = folkwaysMapper.insert(folkways);
        if(result == 0){
            return false;
        }
        return true;
    }
}
