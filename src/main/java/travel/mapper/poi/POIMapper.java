package travel.mapper.poi;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import travel.dao.poi.POI;

import java.util.List;
@Repository
@Mapper
public interface POIMapper {
    /**
     *
     * @param RegionId 通过地区ID查询
     * @return 该地区所有的景点
     */
    @Select("Select * From POI Where Region_Id= #{regionId}")
    List<POI> findByRegionId(@Param("regionId")String RegionId);

    /**
     *
     * @param POIId 通过景点ID查询
     * @return 该景点的信息
     */
    @Select("Select * FROM POI Where POI_Id=#{POI_Id}")
    POI findByPOIId(@Param("POI_Id")String POIId);
}
