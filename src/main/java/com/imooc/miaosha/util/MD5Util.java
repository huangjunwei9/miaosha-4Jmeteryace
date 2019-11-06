package com.imooc.miaosha.util;


import org.apache.commons.codec.digest.DigestUtils;

public class MD5Util {
    public static String md5(String src){
        return DigestUtils.md5Hex(src);
    }

    private static final String salt = "1a2b3c4d";//写死在客户端的固定salt

    /* *
     * 客户端处理：把 用户输入的密码 转化为 form密码，form密码用于提交到服务器
     * PASS = MD5(明文 + 固定Salt)
     * */
    public static String inputPassToFormPass(String intputPass){
//        intputPass = salt.charAt(0) + salt.charAt(2) +  intputPass  + salt.charAt(5)  + salt.charAt(4);
//        String str = intputPass;
        String str = "" + salt.charAt(0) + salt.charAt(2) +  intputPass  + salt.charAt(5)  + salt.charAt(4); //制定MD5组合规则 12 密码 c3
        return md5(str);
    }

    //服务器处理：计算存储到数据库的密码
    public static String formPassToDBFormPass(String formPass, String salt){
        String str = "" + salt.charAt(0) + salt.charAt(2) +  formPass  + salt.charAt(5)  + salt.charAt(4); //制定MD5组合规则 12 密码 c3//"" +用于解决编码不一致的问题
        return md5(str);
    }

    /** 下面的方法仅供测试使用：把 用户输入的密码 转化为 存储到数据库的密码
    *
    * */
    public static String inputPassToDBPass(String inputPass, String saltDB){
        String formPass = inputPassToFormPass(inputPass);
        String dbPass = formPassToDBFormPass(formPass, saltDB);
        return dbPass;
    }

    public static void main(String [] arg){
        System.out.println(inputPassToFormPass("123456"));
        System.out.println(inputPassToDBPass("123456",salt));
    }


}




















