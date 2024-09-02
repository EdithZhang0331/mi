package com.neuedu.miserver.service.impl;

import com.neuedu.miserver.mapper.GoodsMapper;
import com.neuedu.miserver.po.Goods;
import com.neuedu.miserver.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsMapper mapper;

    @Override
    public List<Goods> findGoodsByTypeId(Integer typeId){
        return mapper.findGoodsByTypeId(typeId);
    }

    @Override
    public Goods findGoodsInformation(Integer goodsId){
        return mapper.findGoodsInformation(goodsId);
    }

}
