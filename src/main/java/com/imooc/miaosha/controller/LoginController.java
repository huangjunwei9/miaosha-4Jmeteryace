package com.imooc.miaosha.controller;

import com.alibaba.druid.util.StringUtils;
import com.imooc.miaosha.domain.User;
import com.imooc.miaosha.redis.RedisService;
import com.imooc.miaosha.redis.UserKey;
import com.imooc.miaosha.result.CodeMsg;
import com.imooc.miaosha.result.Result;
import com.imooc.miaosha.service.MiaoshaUserService;
import com.imooc.miaosha.service.UserService;
import com.imooc.miaosha.util.ValidatorUtil;
import com.imooc.miaosha.vo.LoginVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
@RequestMapping(value="/login")
public class LoginController {

    private static Logger log = LoggerFactory.getLogger(LoginController.class);//

    @Autowired
    UserService userService;

    @Autowired
    RedisService redisService;

    @Autowired
    MiaoshaUserService miaoshaUserService;


    //跳转到登录页面
    @RequestMapping("/to_login")
    public String toLogin(){
        return "login";
    }


    /*由于使用了JSR303校验，所以不需要下面的代码*/
/**
    //输完帐号密码后，点击了登录
    @RequestMapping("/do_login")
    @ResponseBody     //@ResponseBody 的作用是直接将json字符串返回到当前页面
    public Result<Boolean> doLogin(LoginVo loginVo){
        log.info(loginVo.toString());

        //参数校验
        String passInput = loginVo.getPassword();
        String mobile =loginVo.getMobile();
        if(StringUtils.isEmpty(passInput)){
            return new Result(CodeMsg.LOGIN_ERROR_PASSWORD_EMPTY);//返回密码不能为空
        }
        if(StringUtils.isEmpty(mobile)){
            return new Result(CodeMsg.LOGIN_ERROR_MOBILE_EMPTY);//返回手机号不能为空
        }
        if(!ValidatorUtil.isMobile(mobile)){
            return new Result(CodeMsg.LOGIN_ERROR_MOBILE_ERROR);//返回手机号格式不对
        }

        //输入验证正确，下一步：登录验证
        CodeMsg codeMsg = miaoshaUserService.login(loginVo);
        if(codeMsg.getCode() == 0){
            return new Result(true);
        }else{
            return new Result(codeMsg);
        }
    }
*/


    /* 输完帐号密码后，点击了登录，使用JSR303进行参数校验
    * @ResponseBody 的作用是直接将json字符串返回到当前页面
    * */
    @RequestMapping("/do_login")
    @ResponseBody
    public Result<CodeMsg> doLoginJSR303(HttpServletResponse httpServletResponse, @Valid LoginVo loginVo){//@Valid去验证loginVo是否符合要求
        log.info(loginVo.toString());

        //输入验证正确，下一步：登录验证
        miaoshaUserService.login(httpServletResponse, loginVo);
        return new Result(CodeMsg.SUCCESS);
    }



    //事务
    @RequestMapping("/db/tx")
    @ResponseBody
    public Result<Boolean> dbTx(){
        Boolean bool = userService.tx();
        return new Result(bool);
    }

    /* 从Jedis容器中获取key对应的value，并把value值转化为User.class
    *
    *
    * */

    @RequestMapping("/redis/get")
    @ResponseBody     //@ResponseBody 的作用是直接将json字符串返回到当前页面
    public Result<User> redisGet(){
        User v1 = redisService.get(UserKey.getById, "" + 1,User.class);
        return new Result(v1);
    }

    //根据id获取用户
    @RequestMapping("/redis/set")
    @ResponseBody     //@ResponseBody 的作用是直接将json字符串返回到当前页面
    public Result<Boolean> redisSet(){
        User user = new User();
        user.setId(1);
        user.setName("1111");

        //使用Jedis设置key = UserKey.getById.getPrefix() + key，  value = user
        boolean ret = redisService.set(UserKey.getById, "" + 1,user); //UserKey:id1
        //String str = redisService.get(UserKey.getById, "" + 1,String.class);
        return new Result(ret);
    }




}










