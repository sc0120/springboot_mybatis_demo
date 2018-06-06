package com.example.demo.controller;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by Caoyixian on 2018/1/26 0026.
 */
@Component
public class CronTask {

//    @Scheduled(cron = "59 * * * * ?")//秒分时日月周
    @Scheduled(cron = "59 * * * * ?")
    public void cronTest() {
        System.out.println("每s执行一次");
    }
}
