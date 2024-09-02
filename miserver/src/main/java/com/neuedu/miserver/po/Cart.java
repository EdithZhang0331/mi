package com.neuedu.miserver.po;

import lombok.Data;

@Data
public class Cart {
    private Integer cartId;
    private Integer goodsId;
    private String telId;
    private Integer quantity;
    private Integer state;
    //关联属性
    private Goods goods;
}
