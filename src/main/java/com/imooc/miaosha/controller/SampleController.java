package com.imooc.miaosha.controller;

import com.imooc.miaosha.domain.User;
import com.imooc.miaosha.redis.RedisService;
import com.imooc.miaosha.redis.UserKey;
import com.imooc.miaosha.result.Result;
import com.imooc.miaosha.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="/demo")
public class SampleController {

    @Autowired
    UserService userService;

    @Autowired
    RedisService redisService;


    @RequestMapping("/thymeleaf")
    public String thymeleaf(Model model){
        model.addAttribute("name","Joshua");
        return "hello";

    }

    //根据id从数据库中获取用户user
    @RequestMapping("/db/get")
    @ResponseBody     //@ResponseBody 的作用是直接将json字符串返回到当前页面
    public Result<User> dbGet(){
        int id = 1;
        User user =  userService.getById(id);
        System.out.println(user.toString());
        return new Result(user);
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










