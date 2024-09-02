package com.neuedu.miserver.controller;

import com.neuedu.miserver.po.Address;
import com.neuedu.miserver.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class AddressController {

    @Autowired
    private AddressService service;

    @RequestMapping("/findDefaultAddress")
    public Address findDefaultAddress(String telId){

        return service.findDefaultAddress(telId);
    }
}
