package travel.mapper.region;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
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
}
