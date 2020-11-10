package travel.service.serviceImpl.folkways;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import travel.dao.folkways.Folkways;
import travel.enums.ResultEnum;
import travel.exceptions.NullException;
import travel.mapper.folkways.FolkwaysMapper;
import travel.service.folkways.FolkwaysService;

import java.util.List;

@Service
public class FolkwaysServiceImpl implements FolkwaysService {
    @Autowired
    private FolkwaysMapper folkwaysMapper;
    @Override
    public List<Folkways> findByRegionId(String RegionId) {
        return folkwaysMapper.findByRegionId(RegionId);
    }
    @Override
    public Folkways findByFolkwaysId(String FolkwaysId) {
        Folkways folkways = folkwaysMapper.findByFolkwaysId(FolkwaysId);
        if(folkways == null){
            throw new NullException(ResultEnum.FOLKWAYS_NOT_EXISTS.getMessage());
        }
        return folkways;
    }
}
