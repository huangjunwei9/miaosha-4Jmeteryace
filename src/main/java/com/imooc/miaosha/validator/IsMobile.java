package com.imooc.miaosha.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 定义一个注解，该注解判断手机号格式是否正确
 * */
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {IsMobileValidator.class}) //注解的处理逻辑是IsMobileValidator.class这个类
public @interface IsMobile {
    boolean required() default true;//允许不传值

    String message() default "手机号格式错误";//校验不通过时输出的message信息

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}













