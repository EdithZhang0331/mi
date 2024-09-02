package com.neuedu.miserver.controller;

import com.neuedu.miserver.po.GoodsType;
import com.neuedu.miserver.service.GoodsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
//解决跨域问题
@CrossOrigin
public class GoodsTypeController {

    @Autowired
    private GoodsTypeService service;

    @RequestMapping("/findGoodsTypeAll")
    public List<GoodsType> findGoodsTypeAll() {

        return service.findGoodsTypeAll();
    }

}
