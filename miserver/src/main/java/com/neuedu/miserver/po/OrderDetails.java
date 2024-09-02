package com.neuedu.miserver.po;

import lombok.Data;

@Data
public class OrderDetails {
    private Integer odId;
    private Integer orderId;
    private Integer goodsId;
    private Integer quantity;

    private Goods goods;
    private Orders orders;
}
