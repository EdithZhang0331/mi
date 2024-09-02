package com.neuedu.miserver.service.impl;

import com.neuedu.miserver.mapper.CartMapper;
import com.neuedu.miserver.po.Cart;
import com.neuedu.miserver.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartMapper mapper;

    @Override
    public int findGoodsCount(Cart cart){
        return mapper.findGoodsCount(cart);
    }

    @Override
    public int updateGoodsCount(Cart cart){

        return mapper.updateGoodsCount(cart);
    }

    @Override
    public int insertGoodsToCart(Cart cart){

        return mapper.insertGoodsToCart(cart);
    }

    @Override
    public int findGoodsCountInCart(String telId){

        return mapper.findGoodsCountInCart(telId);
    }

    @Override
    public List<Cart> findGoodsInCart(String telId){

        return mapper.findGoodsInCart(telId);
    }

    @Override
     public int deleteGoods(Cart cart){

        return mapper.deleteGoods(cart);
     };

    @Override
    public int updateState(Cart cart){

        return mapper.updateState(cart);
    };
}
