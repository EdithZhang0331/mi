package com.neuedu.miserver.mapper;

import com.neuedu.miserver.po.Address;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AddressMapper {

    @Select("select * from address where telId = #{value} and defaultState = 1")
    Address findDefaultAddress(String telId);

    @Select("select * from address where addressId = #{value}")
    Address findAddressInCart(String AddressId);
}
