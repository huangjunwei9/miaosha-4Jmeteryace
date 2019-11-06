package com.imooc.miaosha.exception;

import com.imooc.miaosha.result.CodeMsg;

/*定义一个全局的业务异常*/
public class GlobalException extends RuntimeException{

    private static final long serialVersionID = 1L;

    private CodeMsg codeMsg;

    //构造函数
    public GlobalException(CodeMsg codeMsg){
        super(codeMsg.toString());
        this.codeMsg = codeMsg;
    }

    public static long getSerialVersionID() {
        return serialVersionID;
    }

    public CodeMsg getCodeMsg() {
        return codeMsg;
    }

    public void setCodeMsg(CodeMsg codeMsg) {
        this.codeMsg = codeMsg;
    }
}
