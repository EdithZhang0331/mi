package com.neuedu.miserver.controller;

import com.neuedu.miserver.po.Goods;
import com.neuedu.miserver.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class GoodsController {

    @Autowired
    private GoodsService service;

    @RequestMapping("/findGoodsByTypeId")
    public List<Goods> findGoodsByTypeId(Integer typeId){

        return service.findGoodsByTypeId(typeId);
    }

    @RequestMapping("/findGoodsInformation")
    public Goods findGoodsInformation(Integer goodsId){

        return service.findGoodsInformation(goodsId);
    }

}
