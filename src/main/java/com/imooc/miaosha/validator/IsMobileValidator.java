package com.imooc.miaosha.validator;



import com.alibaba.druid.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IsMobileValidator implements ConstraintValidator<IsMobile, String> {//IsMobile为注解接口，String为待校验的数据的类型

    private boolean required = false; //不可以为空

    @Override
    public void initialize(IsMobile isMobile) {
        required = isMobile.required();//IsMobile默认required()返回true
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(required){
            return com.imooc.miaosha.util.ValidatorUtil.isMobile(value);
        }else { //如果没有调用initialize，那么required == false，执行以下程序：验证手机号是否为空，手机号格式是否正确
            if(StringUtils.isEmpty(value)){
                return false;
            }else{
                return com.imooc.miaosha.util.ValidatorUtil.isMobile(value);
            }
        }


    }
}
