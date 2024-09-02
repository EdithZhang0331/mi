package com.neuedu.miserver.service;

import com.neuedu.miserver.po.Address;

public interface AddressService {

    Address findDefaultAddress(String telId);
}
