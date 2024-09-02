package com.neuedu.miserver.mapper;

import com.neuedu.miserver.po.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface GoodsMapper {

    @Select("SELECT * FROM goods WHERE goodsTypeId = #{value}")
    List<Goods> findGoodsByTypeId(Integer typeId);

    @Select("SELECT * FROM goods WHERE goodsId = #{value}")
    Goods findGoodsInformation(Integer goodsId);
}
