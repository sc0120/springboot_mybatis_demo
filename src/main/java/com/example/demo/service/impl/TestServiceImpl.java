package com.example.demo.service.impl;

import com.example.demo.Entity.TestEntity;
import com.example.demo.mapper.TestMapper;
import com.example.demo.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by caoyixian on 2018/6/5.
 */
@Service(value = "TestService")
public class TestServiceImpl implements TestService {

    @Autowired
    private TestMapper testMapper;

    public int insert(TestEntity testEntity) {
        return testMapper.insert(testEntity);
    }
}
