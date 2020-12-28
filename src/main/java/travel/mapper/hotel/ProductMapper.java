package travel.mapper.hotel;


import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import travel.dao.hotel.Product;

import java.util.List;

@Repository
@Mapper
public interface ProductMapper {
    @Select("Select * from hotelProduct where hotelId=#{hotelId}")
    List<Product> findByHotelId(@Param("hotelId")String hotelId);
    @Insert("Insert into hotelProduct(productId,productName,productPrice,productStock," +
            "hotelId,bedType,internet,bath,area,window) values (#{productId},#{productName},#{productPrice}" +
            ",#{productStock},#{hotelId},#{bedType},#{internet},#{bath},#{area},#{window})")
    int insert(Product product);

    @Update("update hotelProduct set productName=#{productName},productPrice=#{productPrice},productStock=#{productStock}" +
            ",bedType=#{bedType},internet=#{internet},bath=#{bath},area=#{area},window=#{window}")
    int update(Product product);
}
