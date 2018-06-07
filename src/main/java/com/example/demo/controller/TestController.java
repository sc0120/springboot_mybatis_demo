package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.Entity.TestEntity;
import com.example.demo.Entity.User;
import com.example.demo.Entity.UserEntity;
import com.example.demo.mapper.RcUserMapper;
import com.example.demo.repository.RedisRePository;
import com.example.demo.service.TestService;
import com.example.demo.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;


/**
 * Created by caoyixian on 2018/6/2.
 */
@RestController
@RequestMapping(value = "/test")
public class TestController {
    private static final Logger logger = LoggerFactory.getLogger(TestController.class);
    @Autowired
    private UserService userService;
    @Autowired
    private TestService testService;
    @Autowired
    private RcUserMapper rcUserMapper;
    @Autowired
    private RedisRePository redisRePository;

    @RequestMapping(value = "/session_set", method = RequestMethod.GET)
    public Map sessionSet(HttpSession session) throws Exception {
        session.setAttribute("username", "caomomowahaha");
        Map<String, Object> ret = new HashMap<String, Object>();
        ret.put("error_code", 0);
        ret.put("data", new HashMap<>());
        return ret;
    }

    @RequestMapping(value = "/session_get", method = RequestMethod.GET)
    public Map sessionGet(HttpSession session) throws Exception {
        Object sessionValue = session.getAttribute("username");
        Map<String, Object> ret = new HashMap<String, Object>();
        ret.put("error_code", 0);
        ret.put("data", sessionValue);
        return ret;
    }


    @RequestMapping(value = "/add_user", method = RequestMethod.GET)
    public List addUser(@RequestParam(name = "username", required = true) String username,
                       @RequestParam(name = "nickname", required = true) String nickname) {
//        User user = new User();
//        user.setNickname(nickname);
//        user.setUsername(username);
//        return userService.addUser(user);
        UserEntity userEntity = new UserEntity();
        userEntity.setUserName("rcnihao");
        userEntity.setUserNickname("heheh12igei");
        List result = rcUserMapper.UserJoinTest(new Integer(5));
//        List result = rcUserMapper.findAll();
        return result;
//        TestEntity testEntity = new TestEntity();
//        testEntity.setUserId(5);
//        testEntity.setAmount(11);
//        testEntity.setPropInfo(String.format("this is %s",UUID.randomUUID().toString().replace("-","")));
//        return testService.insert(testEntity);
    }

    @RequestMapping(value = "/mybatis_test", method = RequestMethod.GET)
    public Map deleteUser(@RequestParam(name = "page_num", required = true) Integer page_num,
                          @RequestParam(name = "page_size", required = true) Integer page_size) {
        Random random = new Random(System.currentTimeMillis());
        int value = random.nextInt(10);
        logger.info(String.valueOf(value));
        String cacheKey = "mybatis_test_cache";
        boolean checkRedis = redisRePository.exists(cacheKey);
        System.out.println(checkRedis);
        if (!checkRedis) {
            Map<String, Object> ret = new HashMap<String, Object>();
            PageHelper.startPage(page_num, page_size);
            List<User> list = userService.findAll();
            PageInfo pageInfo = new PageInfo(list, page_size);
            ret.put("error_code", 0);
            ret.put("data", new HashMap<String, Object>() {{
                put("pageInfo", pageInfo);
            }});
            redisRePository.set(cacheKey, ret, new Long(100));
            return ret;
        } else {
            Object data = redisRePository.get(cacheKey);
            System.out.println(data);
            return JSON.parseObject(data.toString());
        }

    }

}
