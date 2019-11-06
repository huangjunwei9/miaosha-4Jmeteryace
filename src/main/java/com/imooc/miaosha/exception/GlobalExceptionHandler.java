package com.imooc.miaosha.exception;

import com.imooc.miaosha.result.CodeMsg;
import com.imooc.miaosha.result.Result;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/*定义一个全局的异常的拦截器*/
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler(value=Exception.class)
    public Result<String> exceptionHandler(HttpServletRequest request, Exception e){
        e.printStackTrace();//打印异常信息
        if(e instanceof GlobalException){
            GlobalException ex = (GlobalException) e;
            return new Result(ex.getCodeMsg());
        }else if(e instanceof BindException ){
            BindException ex = (BindException)e;
            List<ObjectError> errors = ex.getAllErrors();
            ObjectError error = errors.get(0); //这里假设只返回一个error
            String msg = error.getDefaultMessage();
            return new Result(CodeMsg.BIND_ERROR.fillArgs(msg));//BIND_ERROR.fillArgs(msg)
        }else{
            return new Result(CodeMsg.SERVER_ERROR);
        }
    }

}
