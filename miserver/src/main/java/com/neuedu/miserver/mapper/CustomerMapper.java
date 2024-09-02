package com.neuedu.miserver.mapper;

import com.neuedu.miserver.po.Customer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CustomerMapper {

    @Select("SELECT * FROM customer WHERE telId = #{telId} and password = #{password}")
    Customer findCustomerByTelAndPsw(Customer customer);
}
