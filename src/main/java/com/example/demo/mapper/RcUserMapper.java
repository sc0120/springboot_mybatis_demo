package com.example.demo.mapper;

import com.example.demo.Entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by caoyixian on 2018/6/6.
 */
@Mapper
public interface RcUserMapper {
    int adduser(UserEntity userEntity);
}
