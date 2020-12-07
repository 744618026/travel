package travel.utils;

import org.springframework.beans.BeanUtils;
import travel.dao.region.Region;
import travel.vo.RegionVo;
import travel.vo.ResultVo;

import java.util.ArrayList;
import java.util.List;

public class Region2RegionVoUtil {
    public static List<RegionVo> covert(List<Region> regionList){
        List<RegionVo> regionVoList = new ArrayList<>();
        for(Region region : regionList){
            RegionVo regionVo = new RegionVo();
            BeanUtils.copyProperties(region,regionVo);
            regionVoList.add(regionVo);
        }
        return regionVoList;
    }
}
