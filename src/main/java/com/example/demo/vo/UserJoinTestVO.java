package com.example.demo.vo;

import com.example.demo.Entity.TestEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by caoyixian on 2018/6/7.
 */
@Data
public class UserJoinTestVO implements Serializable {
    private int id;
    private String userName;
    private String userNickname;
    // 获取关联表记录
    private List<TestEntity> test;
}
