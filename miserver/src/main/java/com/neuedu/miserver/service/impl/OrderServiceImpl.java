package com.neuedu.miserver.service.impl;

import com.neuedu.miserver.mapper.CartMapper;
import com.neuedu.miserver.mapper.OrderDetailsMapper;
import com.neuedu.miserver.mapper.OrderMapper;
import com.neuedu.miserver.po.Orders;
import com.neuedu.miserver.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderDetailsMapper orderDetailsMapper;
    @Autowired
    private CartMapper cartMapper;

    @Override
    public int newOrder (Orders orders){
//        插入订单信息
        orderMapper.insertOrder(orders);
//        插入订单明细信息
        orderDetailsMapper.insertOrderDetails(orders);
//        删除购物车信息
        cartMapper.deleteBoughtGoods(orders.getTelId());
        return orders.getOrderId();
    }

}
