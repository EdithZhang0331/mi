package com.neuedu.miserver.mapper;

import com.neuedu.miserver.po.OrderDetails;
import com.neuedu.miserver.po.Orders;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OrderDetailsMapper {

    @Insert("insert into orderDetails (orderId, goodsId, quantity) select #{orderId}, goodsId, quantity from cart where telId = #{telId} and state = 1")
    int insertOrderDetails(Orders orders);

    @Select("select * from orderDetails where orderId = #{value}")
    @Results({
            @Result(property = "orders", column = "orderId", one=@One(select = "com.neuedu.miserver.mapper.OrderMapper.findPayment")),
            @Result(property = "goods", column = "goodsId", one=@One(select = "com.neuedu.miserver.mapper.GoodsMapper.findGoodsInformation"))
    })
    List<OrderDetails> findPayment(Integer ordersId);
}
