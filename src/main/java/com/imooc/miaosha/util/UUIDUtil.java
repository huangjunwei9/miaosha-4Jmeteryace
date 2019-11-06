package com.imooc.miaosha.util;

import java.util.UUID;

/* 这是一个UUID帮助类，用于生成唯一的值 */
public class UUIDUtil {

    //返回一串唯一标志码
    public static String uuid(){
        return UUID.randomUUID().toString().replace("-","");
    }
}
