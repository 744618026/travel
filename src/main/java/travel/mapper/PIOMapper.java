package travel.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import travel.dao.PIO;

import java.util.List;

@Mapper
public interface PIOMapper {
    @Select("Select * From PIO Where Region_Id= #{regionId}")
    List<PIO> findByRegionId(@Param("regionId")String RegionId);
}
