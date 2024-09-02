package com.neuedu.miserver.controller;

import com.neuedu.miserver.po.Customer;
import com.neuedu.miserver.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class CustomerController {

    @Autowired
    private CustomerService service;

    @RequestMapping("/login")
    public Customer login(String telId, String password){
        Customer customer = new Customer();
        customer.setTelId(telId);
        customer.setPassword(password);
        return service.login(customer);
    }
}
