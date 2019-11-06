package com.imooc.miaosha.config;

import com.alibaba.druid.util.StringUtils;
import com.imooc.miaosha.domain.MiaoshaUser;
import com.imooc.miaosha.service.MiaoshaUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class UserArgumentResolver implements HandlerMethodArgumentResolver{

    @Autowired
    MiaoshaUserService miaoshaUserService;

    /* 测试此 service 是否能使用指定的参数parameter。如果此服务不能使用该参数，则返回 false。
    * 如果此服务能使用该参数、快速测试不可行或状态未知，则返回 true。
    * */
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        Class<?> clazz = parameter.getParameterType();
        return clazz == MiaoshaUser.class ; //是MiaoshaUser则返回true
    }


    /* 从 ModelAndViewContainer(被 @ModelAttribute), NativeWebRequest(其实就是HttpServletRequest) 中获取数据, 解决 方法上的参数
    * 1.获取客户端传过来的Parameter
    * 2.获取客户端传过来的Cookie列表，从Cookie列表中获取name=cookieNameToken所对应的value值（token，即一串唯一标志码）
    * 3.如果Cookie和Param中都没有值，则表示没有登录或者过期了，转去登录页面。否则执行4
    * 4.此时，Cookie和Param中至少有一个有值，优先取param的值，即：如果Param中没有值再取Cookie中的值
    * 5.根据Cookie或者Param （唯一标识码）获取 redis缓存中的用户信息
    * 返回得到的用户信息
    * */
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest httpServletRequest = webRequest.getNativeRequest(HttpServletRequest.class);
        HttpServletResponse httpServletResponse = webRequest.getNativeResponse(HttpServletResponse.class);

        //获取客户端传过来的Parameter和Cookie
        String paramToken = httpServletRequest.getParameter(MiaoshaUserService.COOKIE_NAME_TOKEN);
        String cookieToken = getCookieValue(httpServletRequest, MiaoshaUserService.COOKIE_NAME_TOKEN);

        //如果Cookie和Param中都没有值，则表示没有登录或者过期了，转去登录页面
        if(StringUtils.isEmpty(cookieToken) && StringUtils.isEmpty(paramToken)){
            return "login";
        }

        //此时，Cookie和Param中至少有一个有值，优先取param的值
        String token = StringUtils.isEmpty(paramToken) ? cookieToken:paramToken;

        //从redis缓存中获取该Cookie或者Param所对应的用户信息
        MiaoshaUser miaoshaUser = miaoshaUserService.getByToken(httpServletResponse, token);

        return miaoshaUser;
    }

    //获取Cookie列表中 name = cookieNameToken 对应的 value值
    private String getCookieValue(HttpServletRequest httpServletRequest, String cookieNameToken) {
        Cookie [] cookies = httpServletRequest.getCookies();
        if(cookies == null || cookies.length <= 0){
            return null;
        }
        for(Cookie cookie:cookies){
            if(cookie.getName().equals(cookieNameToken)){
                return cookie.getValue();
            }
        }
        return null;
    }
}
