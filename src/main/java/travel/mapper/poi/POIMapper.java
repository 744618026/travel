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
    @Insert("Insert Into PIO(poiId,poiName,poiDescribe,poiTicketPrice,regionId,createTime)" +
            "values(#{poiId},#{poiName},#{poiDescribe},#{poiTicketPrice},#{regionId},#{createTime, jdbcType=TIMESTAMP})")
    int insert(POI poi);

    /**
     *
     * @param poiId 景点Id
     * @return int
     */
    @Delete("delete from POI where POI_Id=#{POIId}")
    int delete(@Param("POIId")String poiId);

    /**
     *
     * @param poiStock 门票库存
     * @param poiId 景点Id
     * @return int
     */
    @Update("update POI set POI_Stock = #{POIStock} where POI_Id=#{POIId}")
    int updatePOIStock(@Param("POIStock")Integer poiStock,@Param("POIId")String poiId);

    /**
     *
     * @param poiTicketPrice 门票价格
     * @param poiId 景点Id
     * @return int
     */
    @Update("update POI set POI_TicketPrice = #{POITicketPrice} where POI_Id=#{POIId}")
    int updatePOITicketPrice(@Param("POITicketPrice")BigDecimal poiTicketPrice, @Param("POIId")String poiId);
}
