package com.imooc.miaosha.redis;

public interface KeyPrefix {
    public abstract int expireSeconds();
    public abstract String getPrefix();

}
