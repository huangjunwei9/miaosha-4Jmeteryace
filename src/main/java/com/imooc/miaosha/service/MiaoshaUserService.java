package com.imooc.miaosha.service;

import com.alibaba.druid.util.StringUtils;
import com.imooc.miaosha.dao.MiaoshaUserDao;
import com.imooc.miaosha.domain.MiaoshaUser;
import com.imooc.miaosha.exception.GlobalException;
import com.imooc.miaosha.redis.MiaoshaUserKey;
import com.imooc.miaosha.redis.RedisService;
import com.imooc.miaosha.result.CodeMsg;
import com.imooc.miaosha.result.Result;
import com.imooc.miaosha.util.MD5Util;
import com.imooc.miaosha.util.UUIDUtil;
import com.imooc.miaosha.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Service
public class MiaoshaUserService {

    public static final String COOKIE_NAME_TOKEN = "token";

    @Autowired
    RedisService redisService;

    @Autowired
    MiaoshaUserDao miaoshaUserDao;

    //根据手机号获取MiaoshaUser
    public MiaoshaUser getById(Long id){
        return miaoshaUserDao.getById(id);
    }

    //根据token，从redis缓存中取对应的value，即MiaoshaUser对象
    public MiaoshaUser getByToken(HttpServletResponse httpServletResponse, String token) {
        //先做参数校验
        if(StringUtils.isEmpty(token)){
            return null;
        }
        MiaoshaUser miaoshaUser =  redisService.get(MiaoshaUserKey.token, token, MiaoshaUser.class);
        if(miaoshaUser != null){
            //延长Cookie的有效期，即重新把redis缓冲中的值的过期时间设置一下
            addCookie(httpServletResponse, token, miaoshaUser);
        }
        return miaoshaUser;
    }

    //登录验证
    public Result<CodeMsg> login(HttpServletResponse httpServletResponse, LoginVo loginVo) {
        if(loginVo == null){
            throw new GlobalException(CodeMsg.SERVER_ERROR) ; //抛出 系统异常
        }
        String mobile = loginVo.getMobile();
        String formPass = loginVo.getPassword();

        //手机号是否存在
        MiaoshaUser miaoshaUser = getById(Long.parseLong(mobile));
        if(miaoshaUser == null){
            throw new GlobalException(CodeMsg.LOGIN_ERROR_USER_NOT_ERROR); //抛出 手机号不存在
        }

        /* 验证密码是否正确
        * 1.获取数据库中的密码和salt值
        * 2.根据接收到的密码和数据库中的salt 计算出密码，看是否与数据中的密码一致
        * 3.不一致则抛出异常
        * 4.都正确，则在下一步中布置分布式Session到redis
        * */
        String dbPass = miaoshaUser.getPassword(); //数据库中的密码
        String saltDB = miaoshaUser.getSalt();     //数据库中的salt
        String calcPass = MD5Util.formPassToDBFormPass(formPass,saltDB);  //根据接收到的密码和数据库中的salt 计算出密码，看是否与数据中的密码一致
        if (!calcPass.equals(dbPass)){ //如果密码不一致，抛出异常
            throw new GlobalException(CodeMsg.LOGIN_ERROR_PASS_ERROR) ;  //抛出 密码错误
        }

        //此时帐号密码都正确！老铁没毛病。

        /*  下一步：布置分布式session
        * 使用Cookie标识用户，写入到Cookie中，传到客户端，客户端之后的访问都上传该Cookie，服务端拿到该Cookie，获取该Cookie对应的信息，生命周期是：
        * 1.生成Cookie
        * 2.将（Key: 类名称+Cookie）和（Value: 用户信息）键值对放入到第三方redis缓存中
        * 3.设置Cookie的过期时间
        * 4.设置Cookie的根目录
        * 5.将Cookie添加到HttpServletResponse中
        * 注意：过期后 或者 关闭浏览器后，记得释放掉该键值对
        * */
        String token = UUIDUtil.uuid(); //获取一串唯一标志码
        addCookie(httpServletResponse, token, miaoshaUser);

        return new Result(CodeMsg.SUCCESS) ;
    }

    private void addCookie(HttpServletResponse httpServletResponse, String token, MiaoshaUser miaoshaUser){
        redisService.set(MiaoshaUserKey.token,token,miaoshaUser); //设置redisService
        Cookie cookie = new Cookie(COOKIE_NAME_TOKEN,token);//设置Cookie的键值对 name--value  。 name = COOKIE_NAME_TOKEN, value = token
        cookie.setMaxAge(MiaoshaUserKey.token.expireSeconds());//设置Cookie的过期时间为
        cookie.setPath("/");
        httpServletResponse.addCookie(cookie);
    }


}





