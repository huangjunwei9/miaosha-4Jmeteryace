package com.imooc.miaosha.result;

/** CodeMsg 用于保存 异常码和异常信息，由于失败没有数据返回
        *
        */
public class CodeMsg {
    private int code;
    private String msg;

    /**
     * 通过静态方法调用CodeMsg对象获取对应error信息 自定义error 信息
     */



    /*服务端异常*/
    public static CodeMsg SUCCESS = new CodeMsg(0, "成功");
    public static CodeMsg SERVER_ERROR = new CodeMsg(100, "系统异常");
    public static CodeMsg BIND_ERROR = new CodeMsg(500800, "(绑定异常)参数校验异常：%s"); /*用占位符 传入一个参数*/
    public static CodeMsg SESSION_ERROR = new CodeMsg(500111, "没有SESSION！"); /*用占位符 传入一个参数*/
    public static CodeMsg REQUEST_ERROR = new CodeMsg(500789, "非法请求！"); /*用占位符 传入一个参数*/
    public static CodeMsg REQUEST_OVER_LIMIT = new CodeMsg(500999, "请求次数过多！"); /*用占位符 传入一个参数*/

    /*登陆异常 5002xx*/
    public static CodeMsg LOGIN_ERROR_PASSWORD_EMPTY = new CodeMsg(500201, "密码不能为空");
    public static CodeMsg LOGIN_ERROR_MOBILE_EMPTY = new CodeMsg(500202, "手机号不能为空");
    public static CodeMsg LOGIN_ERROR_MOBILE_ERROR = new CodeMsg(500203, "手机号格式错误");
    public static CodeMsg LOGIN_ERROR_USER_NOT_ERROR = new CodeMsg(500203, "手机不存在");
    public static CodeMsg LOGIN_ERROR_PASS_ERROR = new CodeMsg(500203, "密码错误");

    /*秒杀模块异常 5005xx*/
    public static CodeMsg MIAO_SHA_NO_STOCK = new CodeMsg(500501, "库存不足！");
    public static CodeMsg MIAO_SHA_REPEAT = new CodeMsg(500502, "重复秒杀！");
    public static CodeMsg MIAO_SHA_VERICODE = new CodeMsg(500580, "验证法非法！");

    /*订单模块异常 5003xx*/
    public static CodeMsg ORDER_NOT_EXIST = new CodeMsg(500301, "订单不存在");

    /*xx模块异常*/




    /*私有化构造函数
     * 并且使得所有set 方法失效，接口健壮性*/
    private CodeMsg(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    /**
     * 该方法用于返回一个CodeMsg对象 便于 全局异常处理的调用
     * 全局异常处理传入 objects 参数，并返回一个CodeMsg 对象
     * 该方法根据入参 显示 对应的异常code , 以及加入 异常信息的msg显示
     */
    /*利用可变长参数定义 ：适用于 参数类型可知，但是个数未知的情况 */
    public CodeMsg fillArgs(Object... objects) {
        int code = this.code; /* */
        String message = String.format(this.msg, objects);
        return new CodeMsg(code, message);
    }



}
