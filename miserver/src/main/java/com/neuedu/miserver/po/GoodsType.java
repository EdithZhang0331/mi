package com.neuedu.miserver.po;

import lombok.Data;

@Data
public class GoodsType {
    private Integer goodsTypeId;
    private String goodsTypeName;
    private String goodsTypeImg;
    private String remark;
    private Integer delFag;
}