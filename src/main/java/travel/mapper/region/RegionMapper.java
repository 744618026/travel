package travel.mapper.region;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import travel.dao.region.Region;

import java.util.List;

@Repository
@Mapper
public interface RegionMapper {
    @Select("select * from Region")
    List<Region> findAll();
    @Insert("Insert into Region(regionId,regionName,province) values(#{regionId},#{regionName},#{province})")
    int insert(Region region);
    @Select("Select * from Region Where regionId=#{regionId}")
    Region findByRegionId(@Param("regionId") String regionId);
    @Select("Select * from Region where regionName=#{regionName}")
    List<Region> findByName(@Param("regionName")String regionName);
    @Update("update Region set regionName=#{regionName},province=#{province} where regionId=#{regionId}")
    int update(Region region);

    @Delete("delete from Region where regionId=#{regionId}")
    int delete(@Param("regionId")String regionId);
}
