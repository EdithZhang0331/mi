package com.neuedu.miserver.controller;

import com.neuedu.miserver.po.OrderDetails;
import com.neuedu.miserver.po.Orders;
import com.neuedu.miserver.service.OrderDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class OrderDetailsController {

    @Autowired
    private OrderDetailsService service;

    @RequestMapping("/findPayment")
    public List<OrderDetails> findPayment(Integer orderId){

        return service.findPayment(orderId);
    }
}
