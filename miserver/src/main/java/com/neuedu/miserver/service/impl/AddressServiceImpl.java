package com.neuedu.miserver.service.impl;

import com.neuedu.miserver.mapper.AddressMapper;
import com.neuedu.miserver.po.Address;
import com.neuedu.miserver.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {


    @Autowired
    private AddressMapper mapper;

    @Override
    public Address findDefaultAddress(String telId){
        return mapper.findDefaultAddress(telId);
    }

}
