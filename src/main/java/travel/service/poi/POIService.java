package travel.service.poi;

import org.apache.ibatis.annotations.Param;
import travel.dao.poi.POI;

import java.util.List;

public interface POIService {
    /**
     *
     * @param regionId 地区ID
     * @return 该地区所有的景点
     */
    List<POI> findByRegionId(String regionId);

    /**
     *
     * @param POIId 景点ID
     * @return 返回该景点的信息
     */
    POI findByPOIId(String POIId);
    /**
     *
     * @param poi 景点对象
     * @return boolean
     */
    boolean insert(POI poi);
    /**
     *
     * @param POIId 景点Id
     * @return boolean false为失败，true为成功
     */
    boolean delete(String POIId);

    /**
     *
     * @param POIStock 门票库存
     * @param POIId 景点Id
     * @return boolean false为失败，true为成功
     */
    boolean  updatePOIStock(Integer POIStock,String POIId);
}
