package com.example.demo.Entity;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by caoyixian on 2018/4/13.
 */
@Data
public class TestEntity implements Serializable {
   private int id;
   private int userId;
   private int amount;
   private String propInfo;
}
