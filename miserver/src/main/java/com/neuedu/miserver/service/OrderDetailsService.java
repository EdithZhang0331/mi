package com.neuedu.miserver.service;

import com.neuedu.miserver.po.OrderDetails;

import java.util.List;

public interface OrderDetailsService {

    List<OrderDetails> findPayment(Integer orderId);
}
