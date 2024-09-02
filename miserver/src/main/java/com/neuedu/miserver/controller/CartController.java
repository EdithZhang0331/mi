package com.neuedu.miserver.controller;

import com.neuedu.miserver.po.Cart;
import com.neuedu.miserver.po.Result;
import com.neuedu.miserver.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class CartController {

    @Autowired
    private CartService service;


    @RequestMapping("/findGoodsCount")
    public Result findGoodsCount(@RequestBody Cart cart){   //@RequestBody用于转换json为对象
//        System.out.println(cart.getGoodsId());
        Result res = new Result();
        res.setResData(service.findGoodsCount(cart));
        return res;
    }

    @RequestMapping("/updateGoodsCount")
    public Result updateGoodsCount(@RequestBody Cart cart){
        Result res = new Result();
        res.setResData(service.updateGoodsCount(cart));
        return res;
    }

    @RequestMapping("/insertGoodsToCart")
    public Result insertGoodsToCart(@RequestBody Cart cart){
        Result res = new Result();
        res.setResData(service.insertGoodsToCart(cart));
        return res;
    }

    @RequestMapping("/findGoodsCountInCart")
    public Result findGoodsCountInCart(String telId){
        Result res = new Result();
        res.setResData(service.findGoodsCountInCart(telId));
        System.out.println(res.getResData());
        return res;
    }

    @RequestMapping("/findGoodsInCart")
    public List<Cart> findGoodsInCart(String telId){

        return service.findGoodsInCart(telId);
    }

    @RequestMapping("/deleteGoods")
    public Result deleteGoods(@RequestBody Cart cart){
        Result res = new Result();
        res.setResData(service.deleteGoods(cart));
        return res;
    };

    @RequestMapping("/updateState")
    public Result updateState(@RequestBody Cart cart){
        Result res = new Result();
        res.setResData(service.updateState(cart));
        return res;
    }
}
