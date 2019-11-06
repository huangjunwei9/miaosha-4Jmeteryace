package com.imooc.miaosha.redis;

public class UserKey extends BasePrefix {

    private UserKey(int expireSeconds, String prefix) {
        //继承父类的构造函数，传递参数（过期时间=expireSeconds，前缀=prefix）
        super(expireSeconds, prefix);
    }

    private UserKey(String prefix) {
        //继承父类的构造函数，传递参数（过期时间=0，前缀=prefix）
        super(prefix);
    }

    public static UserKey getById = new UserKey("id");  //非单例模式，建立static类型的UserKey对象（prefix="id"）
    public static UserKey getByName = new UserKey("name");//非单例模式，建立static类型的UserKey对象（prefix="name"）

}
