package com.example.demo.Entity;

import java.io.Serializable;

/**
 * Created by caoyixian on 2018/6/5.
 */

public class UserJoinAmount  extends User implements Serializable{

    private String amount;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
