package com.neuedu.miserver.mapper;

import com.neuedu.miserver.po.Cart;
import com.neuedu.miserver.po.Goods;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CartMapper {

    @Select("select count(*) from cart where goodsId = #{goodsId} and telId = #{telId}")
    int findGoodsCount(Cart cart);

    @Update("update cart set quantity = quantity + #{quantity} where goodsId = #{goodsId} and telId = #{telId}")
    int updateGoodsCount(Cart cart);

    @Insert("INSERT INTO cart(goodsId, telId, quantity, state) VALUES (#{goodsId}, #{telId}, #{quantity}, #{state})")
    int insertGoodsToCart(Cart cart);

    @Select("select count(*) from cart where telId = #{value}")
    int findGoodsCountInCart(String telId);

    @Select("select * from cart where telId = #{value}")
    @Results({@Result(property = "goods", column = "goodsId", one=@One(select = "com.neuedu.miserver.mapper.GoodsMapper.findGoodsInformation"))})
    List<Cart> findGoodsInCart(String telId);

    @Delete("delete from cart where goodsId = #{goodsId} and telId = #{telId}")
    int deleteGoods(Cart cart);

    @Update("update cart set state = #{state} where goodsId = #{goodsId} and telId = #{telId}")
    int updateState(Cart cart);

    @Delete("delete from cart where telId = #{value} and state = 1")
    int deleteBoughtGoods(String telId);
}
