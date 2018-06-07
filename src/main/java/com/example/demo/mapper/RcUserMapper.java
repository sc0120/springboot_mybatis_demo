package com.example.demo.mapper;

import com.example.demo.Entity.UserEntity;
import com.example.demo.Entity.UserTestEntityVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by caoyixian on 2018/6/6.
 */
@Mapper
@Component(value = "RcUserMapper")
public interface RcUserMapper {
    int adduser(UserEntity userEntity);
    List<UserEntity> findAll();
    List<UserTestEntityVO> UserJoinTest(Integer userId);
}
