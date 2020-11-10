package travel.mapper.poi;


import org.apache.ibatis.annotations.*;
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

    /**
     *
     * @param poi 景点对象
     * @return int
     */
    @Insert("Insert Into PIO(POI_Id,POI_Name,POI_Describe,POI_TicketPrice,Region_Id,Create_Time)" +
            "values(#{POIId},#{POIName},#{POIDescribe},#{POITicketPrice},#{RegionId},#{CreateTime, jdbcType=TIMESTAMP})")
    int insert(POI poi);

    /**
     *
     * @param POIId 景点Id
     * @return int
     */
    @Delete("delete from POI where POI_Id=#{POIId}")
    int delete(@Param("POIId")String POIId);

    /**
     *
     * @param POIStock 门票库存
     * @param POIId 景点Id
     * @return int
     */
    @Update("update POI set POI_Stock = #{POIStock} where POI_Id=#{POIId}")
    int updatePOIStock(@Param("POIStock")Integer POIStock,@Param("POIId")String POIId);
}
