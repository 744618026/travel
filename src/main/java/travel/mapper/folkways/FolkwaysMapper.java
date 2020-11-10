package travel.mapper.folkways;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import travel.dao.folkways.Folkways;

import java.util.List;

@Repository
@Mapper
public interface FolkwaysMapper {
    /**
     *
     * @param RegionId 地区Id
     * @return 该地区所有民俗
     */
    List<Folkways> findByRegionId(String RegionId);

    /**
     *
     * @param FolkwaysId 民俗Id
     * @return 该民俗信息
     */
    Folkways findByFolkwaysId(String FolkwaysId);
}
