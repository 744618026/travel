package travel.service.poi;

import org.apache.ibatis.annotations.Param;
import travel.dao.poi.POIImage;

import java.util.List;

public interface POIImageService {
    /**
     *
     * @param poiId 景点ID
     * @return 返回该景点所有图片
     */
    List<POIImage> findByPOIId(String poiId);
    /**
     *
     * @param imageId 图片Id
     * @return boolean
     */
    boolean delete(Integer imageId);

    /**
     *
     * @param imageId 图片Id
     * @return 该图片信息
     */
    POIImage findByImageId(Integer imageId);

    /**
     *
     * @param poiImage 图片对象
     * @return boolean
     */
    boolean insert(POIImage poiImage);
}
