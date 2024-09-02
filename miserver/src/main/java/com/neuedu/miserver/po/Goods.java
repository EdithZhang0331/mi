package com.neuedu.miserver.po;

import lombok.Data;

@Data
public class Goods {
    private Integer goodsId;
    private String goodsName;
    private String goodsExplain;
    private String goodsImg;
    private Double goodsPrice;
    private Integer goodsTypeId;
}
