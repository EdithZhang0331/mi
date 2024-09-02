package com.neuedu.miserver.service;

import com.neuedu.miserver.po.Goods;

import java.util.List;

public interface GoodsService {

    List<Goods> findGoodsByTypeId(Integer typeId);
    Goods findGoodsInformation(Integer goodsId);
}
