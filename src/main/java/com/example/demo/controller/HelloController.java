package com.example.demo.controller;

import com.example.demo.repository.CenterDbRepository;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by Administrator on 2017/12/18 0018.
 */
@RestController
@RequestMapping(value = "/hello")
public class HelloController {
    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);
    @Autowired
    private CenterDbRepository centerDbRepository;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public Map getTest(@RequestHeader Map<String,Object> headers,
                       HttpServletRequest request) throws Exception {
        logger.info(request.getServletContext().getRealPath("images/"));
        int autoId = centerDbRepository.addNewUser("ababab","heheheh111");
        System.out.println(autoId);
        System.out.println(headers.size());
        HashMap ret = new HashMap();
        String path = ClassUtils.getDefaultClassLoader().getResource("").getPath() + "images";
        logger.info("test logger");
        ret.put("path", path);
        return ret;
    }

    @RequestMapping(value = "/get_user_by_id", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    public Map getUserById(@RequestParam(name = "id", defaultValue = "") String id) throws Exception {
        HashMap ret = new HashMap();
        if (id.isEmpty()) {
            ret.put("code", 1);
            ret.put("msg", "params is lost");
            return ret;
        }
        Map userInfo = centerDbRepository.getUserById(Integer.valueOf(id));

        ret.put("code", 0);
        ret.put("data", userInfo);
        return ret;

    }

    @RequestMapping(value = "/get_user_list", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    public Map getUserList() throws Exception {
        List userList = centerDbRepository.getUserList(0, 10);
        HashMap ret = new HashMap();
        ret.put("code", 0);
        ret.put("data", userList);
        return ret;
    }


}
