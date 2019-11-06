package com.imooc.miaosha.redis;

public class MiaoshaUserKey extends BasePrefix {

    private static final int TOKEN_EXPIRE = 1800; //设置TOKEN的过期时间为60秒*30 =1800秒

    public MiaoshaUserKey(String prefix) {
        super(prefix);
    }

    public MiaoshaUserKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    public static MiaoshaUserKey token0 = new MiaoshaUserKey("token");  //非单例模式，建立static类型的UserKey对象（prefix="id"）
    public static MiaoshaUserKey token = new MiaoshaUserKey( TOKEN_EXPIRE,"token");  //非单例模式，建立static类型的UserKey对象（prefix="id"）
}
