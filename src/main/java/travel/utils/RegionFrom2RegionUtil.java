package travel.utils;

import org.springframework.beans.BeanUtils;
import travel.dao.region.Region;
import travel.dataForm.RegionForm;

public class RegionFrom2RegionUtil {
    public static Region convert(RegionForm regionForm){
        Region region = new Region();
        BeanUtils.copyProperties(regionForm,region);
        return region;
    }
}
