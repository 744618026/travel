package travel.mapper.poi;


import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import travel.dao.poi.POI;

import java.math.BigDecimal;
import java.util.List;
@Repository
@Mapper
public interface POIMapper {
    /**
     *
     * @param regionId 通过地区ID查询
     * @return 该地区所有的景点
     */
    @Select("Select * From POI Where regionId= #{regionId}")
    List<POI> findByRegionId(@Param("regionId")String regionId);

    /**
     *
     * @param poiId 通过景点ID查询
     * @return 该景点的信息
     */
    @Select("Select * FROM POI Where poiId=#{poiId}")
    POI findByPOIId(@Param("poiId")String poiId);

    /**
     *
     * @param poi 景点对象
     * @return int
     */
    @Insert("Insert Into POI(poiId,poiName,poiDescribe,poiTicketPrice,poiStock,regionId,createTime)" +
            "values(#{poiId},#{poiName},#{poiDescribe},#{poiTicketPrice},#{poiStock},#{regionId},#{createTime, jdbcType=TIMESTAMP})")
    int insert(POI poi);

    /**
     *
     * @param poiId 景点Id
     * @return int
     */
    @Delete("delete from POI where poiId=#{poiId}")
    int delete(@Param("poiId")String poiId);
    /**
     *
     * @param poi
     * @return int
     */
    @Update("update POI set poiName=#{poiName},poiDescribe=#{poiDescribe},regionId=#{regionId},poiTicketPrice=#{poiTicketPrice},poiStock=#{poiStock} where poiId=#{poiId}")
    int update(POI poi);

    @Select("Select count * from POI where regionId=#{regionId}")
    Integer countById(@Param("regionId")String regionId);
}
