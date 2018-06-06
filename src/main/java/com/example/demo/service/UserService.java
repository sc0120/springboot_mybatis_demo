package com.example.demo.service;

import com.example.demo.Entity.User;

import java.util.List;
import java.util.Map;

/**
 * Created by caoyixian on 2018/6/2.
 */
public interface UserService {
    int addUser(User user);
    int deleteUser(Integer id);
    User findById(Integer id);
    List<User> findAll();
    List<Map> findByIdJoinTest(Integer id);
}
