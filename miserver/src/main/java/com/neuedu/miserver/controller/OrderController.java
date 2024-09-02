package com.neuedu.miserver.controller;

import com.neuedu.miserver.po.Orders;
import com.neuedu.miserver.po.Result;
import com.neuedu.miserver.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class OrderController {

    @Autowired
    private OrderService service;

    @RequestMapping("/insertOrder")
    public Result insertOrder(@RequestBody Orders orders){
        Result res = new Result();
        res.setResData(service.newOrder(orders));
        System.out.println(res);
        return res;
    }

}
