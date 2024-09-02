package com.neuedu.miserver.service.impl;

import com.neuedu.miserver.mapper.OrderDetailsMapper;
import com.neuedu.miserver.po.OrderDetails;
import com.neuedu.miserver.service.OrderDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailsServiceImpl implements OrderDetailsService {

    @Autowired
    private OrderDetailsMapper mapper;

    @Override
    public List<OrderDetails> findPayment(Integer orderId){
        return mapper.findPayment(orderId);
    }
}
