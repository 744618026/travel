package travel.service.folkways;

import travel.dao.folkways.Folkways;

import java.util.List;

public interface FolkwaysService {
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
