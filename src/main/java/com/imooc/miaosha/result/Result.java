package com.imooc.miaosha.result;

import java.io.Serializable;

/**
 * 用于封装服务器到客户端的Json返回值
 * 参考网址：https://blog.csdn.net/linmengmeng_1314/article/details/81748966
 *
 */
public class Result<T> implements Serializable {
    //Serializable将对象的状态保存在存储媒体中以便可以在以后重新创建出完全相同的副本
    public static final int SUCCESS=0;
    public static final int ERROR=1;
    public static final int OTHER=2;

    private int state;
    private String message = "";
    private T data;
    private String pass="";

    public Result(){
        state = SUCCESS;
    }
    //为了方便，重载n个构造器
    public Result(int state, String message, T data) {
        super();
        this.state = state;
        this.message = message;
        this.data = data;
    }
    public Result(int state,String error){
        this(state,error,null);
    }
    public Result(int state,T data){
        this(state,"",data);
    }
    public Result(String error){
        this(ERROR,error,null);
    }

    public Result(T data){
        this(SUCCESS,"",data);
    }
    public Result(int state){
        this(state,"",null);
    }
    public Result(Throwable e){
        this(ERROR,e.getMessage(),null);
    }
    public int getState() {
        return state;
    }
    public void setState(int state) {
        this.state = state;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }

    public static int getSuccess() {
        return SUCCESS;
    }
    public static int getError() {
        return ERROR;
    }

    public static Boolean errorBoolean(){
        return false;
    }

    public static String errorString(String msg){
        return msg;
    }

    @Override
    public String toString() {
        return "Result [state=" + state + ", message=" + message + ", pass=" + pass + ", data=" + data + "]";
    }
}

