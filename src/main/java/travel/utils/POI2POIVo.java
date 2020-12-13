package travel.utils;
import org.springframework.beans.BeanUtils;
import travel.dao.poi.POI;
import travel.vo.POIVo;
public class POI2POIVo {
    public static POIVo convert(POI poi){
        POIVo poiVo = new POIVo();
        BeanUtils.copyProperties(poi,poiVo);
        return poiVo;
    }
}
