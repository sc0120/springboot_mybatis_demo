package com.example.demo.mapper;

import com.example.demo.Entity.UserEntity;
import com.example.demo.vo.UserJoinTestVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by caoyixian on 2018/6/6.
 */
@Mapper
@Component(value = "RcUserMapper")
public interface RcUserMapper {
    int adduser(UserEntity userEntity);
    List<UserEntity> findAll(List ids);
    UserEntity findOne(@Param("params") Map params);
    List<UserJoinTestVO> UserJoinTest(Integer userId);
}
