package com.neuedu.miserver.service.impl;

import com.neuedu.miserver.mapper.GoodsTypeMapper;
import com.neuedu.miserver.po.GoodsType;
import com.neuedu.miserver.service.GoodsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsTypeServiceImpl implements GoodsTypeService {

    @Autowired
    private GoodsTypeMapper mapper;

    @Override
    public List<GoodsType> findGoodsTypeAll(){
        return mapper.findGoodsTypeAll();
    }

}
