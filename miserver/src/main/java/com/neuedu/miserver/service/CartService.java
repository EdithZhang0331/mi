package com.neuedu.miserver.service;

import com.neuedu.miserver.po.Cart;

import java.util.List;

public interface CartService {
    int findGoodsCount(Cart cart);

    int updateGoodsCount(Cart cart);

    int insertGoodsToCart(Cart cart);

    int findGoodsCountInCart(String telId);

    List<Cart> findGoodsInCart(String telId);

    int deleteGoods(Cart cart);

    int updateState(Cart cart);
}
