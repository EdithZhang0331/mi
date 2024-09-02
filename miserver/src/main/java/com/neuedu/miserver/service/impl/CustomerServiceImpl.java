package com.neuedu.miserver.service.impl;

import com.neuedu.miserver.mapper.CustomerMapper;
import com.neuedu.miserver.po.Customer;
import com.neuedu.miserver.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerMapper mapper;

    @Override
    public Customer login(Customer customer){

        return mapper.findCustomerByTelAndPsw(customer);
    }
}
