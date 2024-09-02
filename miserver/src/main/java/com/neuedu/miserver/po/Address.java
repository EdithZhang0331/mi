package com.neuedu.miserver.po;

import lombok.Data;

@Data
public class Address {
    private Integer addressId;
    private String contactName;
    private String contactTel;
    private String address;
    private String telId;
    private Integer defaultState;
}
