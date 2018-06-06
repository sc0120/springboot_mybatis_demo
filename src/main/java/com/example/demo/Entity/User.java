package com.example.demo.Entity;

import java.io.Serializable;

/**
 * Created by caoyixian on 2018/4/13.
 */
public class User implements Serializable {
    private int id;
    private String username;
    private String nickname;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
