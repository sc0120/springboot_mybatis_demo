package com.example.demo.mapper;

import com.example.demo.Entity.TestEntity;
import com.example.demo.Entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.springframework.stereotype.Component;

/**
 * Created by caoyixian on 2018/6/5.
 */
@Mapper
@Component(value = "TestMapper")
public interface TestMapper {

    @Insert("INSERT INTO rc_test(user_id,amount,prop_info) VALUES (#{userId}, #{amount},#{propInfo})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(TestEntity testEntity);

}
