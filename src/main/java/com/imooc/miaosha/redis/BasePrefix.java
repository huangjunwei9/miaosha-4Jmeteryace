package com.imooc.miaosha.redis;

public abstract class BasePrefix implements KeyPrefix {
    private int expireSeconds; //过期时间

    private String prefix; //key的前缀

    public BasePrefix(String prefix) {//默认0代表永不过期
        this.expireSeconds = 0;
        this.prefix = prefix;
    }

    public BasePrefix(int expireSeconds, String prefix) {
        this.expireSeconds = expireSeconds;
        this.prefix = prefix;
    }

    @Override
    public int expireSeconds() { //默认0代表永不过期
        return expireSeconds;
    }

    @Override
    public String getPrefix() { //返回className + ":"+ prefix;
        String className = getClass().getSimpleName();
        return className + ":"+ prefix;
    }
}
