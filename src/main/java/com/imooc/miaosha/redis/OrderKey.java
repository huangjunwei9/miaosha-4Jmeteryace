package com.imooc.miaosha.redis;

//订单
public class OrderKey extends BasePrefix{

    public OrderKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }
}
