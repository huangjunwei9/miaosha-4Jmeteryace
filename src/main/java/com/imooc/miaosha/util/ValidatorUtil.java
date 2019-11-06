package com.imooc.miaosha.util;


import com.alibaba.druid.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidatorUtil {
    private static final Pattern mobile_pattern = Pattern.compile("1\\d{10}"); //1后面跟了10个数字

    public static boolean isMobile(String src){
        if(StringUtils.isEmpty(src)){
            return false;
        }
        Matcher m = mobile_pattern.matcher(src);
        System.out.println(m);
        return m.matches();
    }

    public static  void  main(String[] args){
        System.out.println(isMobile("15705426258"));
        System.out.println(isMobile("1570542625"));
    }

}
