package travel.mapper.folkways;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import travel.dao.folkways.Folkways;

import java.util.List;

@Repository
@Mapper
public interface FolkwaysMapper {
    /**
     *
     * @param regionId 地区Id
     * @return 该地区所有民俗
     */
    @Select("Select * from Folkways where Region_Id=#{RegionId}")
    List<Folkways> findByRegionId(@Param("RegionId") String regionId);

    /**
     *
     * @param folkwaysId 民俗Id
     * @return 该民俗信息
     */
    @Select("Select * from Folkways where Folkways_Id=#{FolkwaysId}")
    Folkways findByFolkwaysId(@Param("FolkwaysId") String folkwaysId);

    /**
     *
     * @param folkways 民俗对象
     * @return int
     */
    @Insert("Insert Into Folkways(Folkways_Id,Folkways_Name,Region_Id,Folkways_Content) values(#{FolkwaysId},#{FolkwaysName},#{Region_Id},#{FolkwaysContent})")
    int insert(Folkways folkways);
}
