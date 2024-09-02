package com.neuedu.miserver.mapper;

import com.neuedu.miserver.po.Orders;
import org.apache.ibatis.annotations.*;

@Mapper
public interface OrderMapper {

    @Insert("insert into orders(telId, orderDate, orderTotal, addressId, orderState) values (#{telId}, #{orderDate}, #{orderTotal}, #{addressId}, #{orderState})")
    @Options(useGeneratedKeys = true, keyProperty = "orderId")
    int insertOrder(Orders orders);

    @Select("select * from orders where orderId = #{value}")
    @Results({@Result(property = "address", column = "addressId", one=@One(select = "com.neuedu.miserver.mapper.AddressMapper.findAddressInCart"))})
    Orders findPayment(Integer orderId);
}
