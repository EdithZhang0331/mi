package com.neuedu.miserver.po;

import lombok.Data;

import java.util.Date;

@Data
public class Orders {
    private Integer orderId;
    private String telId;
    private Date orderDate;
    private Double orderTotal;
    private Integer addressId;
    private Integer orderState;

    private Address address;
    private OrderDetails orderDetails;
}
