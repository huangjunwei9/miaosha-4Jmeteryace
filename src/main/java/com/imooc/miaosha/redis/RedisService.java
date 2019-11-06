package com.imooc.miaosha.redis;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Service
public class RedisService {

    @Autowired
    JedisPool jedisPool; //从容器中获取一个JedisPool对象


    /* 使用redis缓存器 设置key-value键值对，
     * 键：String realKey = prefix.getPrefix() + key;
     * 值：str，String str是由T类型的value转化而来
     * */
    public <T> boolean set(KeyPrefix prefix, String key, T value){
        Jedis jedis = null;

        //由于用到了连接池，用完后必须释放掉连接池，否则容易导致连接池不够用
        try {
            jedis = jedisPool.getResource();  //返回Jedis对象
            String strValue = beanToString(value);
            if(strValue == null || strValue.length()<=0){
                return false;
            }

            //生成真正的Key
            String realKey = prefix.getPrefix() + key;

            int seconds = prefix.expireSeconds();//过期时间
            if(seconds <= 0){//永不过期
                jedis.set(realKey,strValue);
            } else{//过期时间为seconds
                jedis.setex(realKey,seconds,strValue);
            }
            return true;
        }finally {
            returnToPool(jedis);
        }

        //return jedis;
    }


    /* 从jedisPool缓存池中的jedis缓存器中获取key键对应的value值（T类型）
     * 比如，T为User
     *
     *
     * */
    public <T> T get(KeyPrefix prefix, String key, Class<T> clazz){
        Jedis jedis = null;

        //由于用到了JedisPool连接池，用完后必须释放掉连接池，否则容易导致连接池不够用，所以使用try{}finally{}结构
        try {
            jedis = jedisPool.getResource();

            //生成真正的Key
            String realKey = prefix.getPrefix() + key;

            String strValue = jedis.get(realKey);//获取Jedis容器中realkey键对应的value
            T t = stringToBean(strValue, clazz); //将字符串str转化为clazz对应的Bean对象，返回该Bean对象
            return t;
        }finally {
            returnToPool(jedis);//释放掉连接池
        }

    }


    /*判断key是否存在*/
    public <T> boolean exists(KeyPrefix prefix, String key){
        Jedis jedis = null;

        //由于用到了JedisPool连接池，用完后必须释放掉连接池，否则容易导致连接池不够用，所以使用try{}finally{}结构
        try {
            jedis = jedisPool.getResource();

            //生成真正的Key
            String realKey = prefix.getPrefix() + key;
            return jedis.exists(realKey); //已存在realKey则返回true，否则返回false
        }finally {
            returnToPool(jedis);//释放掉连接池
        }
    }

    /*增加一个key*/
    public <T> Long incr(KeyPrefix prefix, String key){
        Jedis jedis = null;

        //由于用到了JedisPool连接池，用完后必须释放掉连接池，否则容易导致连接池不够用，所以使用try{}finally{}结构
        try {
            jedis = jedisPool.getResource();

            //生成真正的Key
            String realKey = prefix.getPrefix() + key;
            return jedis.incr(realKey); //已存在realKey则返回true，否则返回false
        }finally {
            returnToPool(jedis);//释放掉连接池
        }
    }

    /*Jedis删除一个key*/
    public <T> Long decr(KeyPrefix prefix, String key){
        Jedis jedis = null;

        //由于用到了JedisPool连接池，用完后必须释放掉连接池，否则容易导致连接池不够用，所以使用try{}finally{}结构
        try {
            jedis = jedisPool.getResource();

            //生成真正的Key
            String realKey = prefix.getPrefix() + key;
            return jedis.decr(realKey); //已存在realKey则返回true，否则返回false
        }finally {
            returnToPool(jedis);//释放掉连接池
        }
    }


    //将value对应的T类型的Bean对象 转化为String类型变量，返回该String变量 ---------用于set函数
    private <T> String beanToString(T value) {
        if(value == null){
            return null;
        }
        Class<?> clazz = value.getClass();
        if(clazz == int.class || clazz == Integer.class){
            return "" + value;
        }else if(clazz == String.class){
            return (String)value;
        }else if(clazz == long.class || clazz == Long.class){
            return "" + value;
        }else{
            return JSON.toJSONString(value);
        }
    }


    //将字符串str转化为clazz对应的Bean对象，返回该Bean对象----------get
    private <T> T stringToBean(String str, Class<T> clazz) {
        if(str == null || str.length()<=0){
            return null;
        }
//      Class<?> clazz = value.getClass();
        if(clazz == int.class || clazz == Integer.class){
            return (T)Integer.valueOf(str);
        }else if(clazz == String.class){
            return (T)str;
        }else if(clazz == long.class || clazz == Long.class){
            return (T)Long.valueOf(str);
        }else{
            return JSON.toJavaObject(JSON.parseObject(str), clazz); //
        }

    }

    //用于释放连接池
    private void returnToPool(Jedis jedis) {
        if(jedis != null){
            jedis.close();
        }
    }

}












