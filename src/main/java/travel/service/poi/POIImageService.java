package travel.service.poi;

import travel.dao.poi.POIImage;

import java.util.List;

public interface POIImageService {
    /**
     *
     * @param POIId 景点ID
     * @return 返回该景点所有图片
     */
    List<POIImage> findByPOIId(String POIId);
}
