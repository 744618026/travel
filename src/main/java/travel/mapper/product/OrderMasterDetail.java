package travel.mapper.product;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import travel.dao.OrderDetail;
import travel.dao.OrderMaster;

@Repository
@Mapper
public interface OrderMasterDetail {
    @Insert("Insert into orderMaster(orderId,totalPrice,orderStatus,username,payStatus,) values(" +
            "#{orderId},#{totalPrice},#{orderStatus},#{username},#{paystatus})")
    int insert(OrderMaster orderMaster);
    @Insert("Insert into (detailId,orderId,productId,productType) values(" +
            "#{detailId},#{orderId},#{productId},#{productType})")
    int insert(OrderDetail orderDetail);
}
