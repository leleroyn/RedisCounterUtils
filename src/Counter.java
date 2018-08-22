package com.ucs.csxbank.websit.wap.util;

import java.util.Date;

/**
 * @Author:luoyong
 * @Date: 2018/8/22 15:01
 */
public class Counter {
    private   String name ;

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    private Date expireTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
