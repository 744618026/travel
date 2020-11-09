package travel.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import travel.dao.POI;

import java.util.List;
@Repository
@Mapper
public interface POIMapper {
    @Select("Select * From POI Where Region_Id= #{regionId}")
    List<POI> findByRegionId(@Param("regionId")String RegionId);
    @Select("Select * FROM POI Where POI_Id=#{POI_Id}")
    POI findByPOIId(@Param("POI_Id")String POIId);
}
