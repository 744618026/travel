package travel.service.folkways;

import travel.dao.folkways.Folkways;

import java.util.List;

public interface FolkwaysService {
    /**
     *
     * @param regionId 地区Id
     * @return 该地区所有民俗
     */
    List<Folkways> findByRegionId(String regionId);

    /**
     *
     * @param folkwaysId 民俗Id
     * @return 该民俗信息
     */
    Folkways findByFolkwaysId(String folkwaysId);
    /**
     *
     * @param folkways 民俗对象
     * @return boolean
     */
    boolean insert(Folkways folkways);
}
