package com.neuedu.miserver.mapper;

import com.neuedu.miserver.po.GoodsType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface GoodsTypeMapper {

    @Select("SELECT * FROM goodstype")
    List<GoodsType> findGoodsTypeAll();
}
