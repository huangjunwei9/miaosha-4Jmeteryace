package com.imooc.miaosha.controller;

import com.alibaba.druid.util.StringUtils;
import com.imooc.miaosha.domain.MiaoshaUser;
import com.imooc.miaosha.domain.User;
import com.imooc.miaosha.redis.RedisService;
import com.imooc.miaosha.redis.UserKey;
import com.imooc.miaosha.result.CodeMsg;
import com.imooc.miaosha.result.Result;
import com.imooc.miaosha.service.GoodsService;
import com.imooc.miaosha.service.MiaoshaUserService;
import com.imooc.miaosha.service.UserService;
import com.imooc.miaosha.vo.GoodsVo;
import com.imooc.miaosha.vo.LoginVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value="/goods")
public class GoodsController {

    @Autowired
    GoodsService goodsService;

    private static Logger log = LoggerFactory.getLogger(GoodsController.class);//




    /* 不再使用如下代码，使用WebConfig配置Session，以提高代码复用性*/
    /**
    @RequestMapping("/to_list")
    public String toGoodsList(HttpServletResponse httpServletResponse, Model model, @CookieValue(value = MiaoshaUserService.COOKIE_NAME_TOKEN, required = false) String cookieToken,
                              @RequestParam(value = MiaoshaUserService.COOKIE_NAME_TOKEN, required = false)String paramToken,
                              MiaoshaUser miaoshaUserParam){
        //如果Cookie和Param中都没有值，则表示没有登录或者过期了，转去登录页面
        if(StringUtils.isEmpty(cookieToken) && StringUtils.isEmpty(paramToken)){
            return "login";
        }

        //此时，Cookie和Param中至少有一个有值，优先取paramToken的值
        String token = StringUtils.isEmpty(paramToken) ? cookieToken:paramToken;

        //获取该Cookie或者Param所对应的用户信息
        MiaoshaUser miaoshaUser = miaoshaUserService.getByToken(httpServletResponse, token);

        model.addAttribute("user", miaoshaUser);
        return "goods_list";
    }
     */


    //商品列表页
    @RequestMapping("/to_list")
    public String toGoodsList(Model model,MiaoshaUser miaoshaUser){
        model.addAttribute("user", miaoshaUser);

        //查询商品列表
        List<GoodsVo> goodsList = goodsService.listGoodsVo();
        model.addAttribute("goodsList", goodsList);

        System.out.println(miaoshaUser.getNickname());
        return "goods_list";
    }


    // 点击了商品列表的“详情”
    // 作用：查看商品详情页
    @RequestMapping("/to_detail/{goodsId}")
    public String toGoodsDetail(Model model, MiaoshaUser miaoshaUser, @PathVariable("goodsId")Long goodsId){
        model.addAttribute("user", miaoshaUser);

        //查询商品详情，并返回商品详情
        GoodsVo goodsVo = goodsService.getGoodsVoByGoodsId(goodsId);
        model.addAttribute("goods", goodsVo);

        //获取秒杀开始/结束时间
        long startAt = goodsVo.getStartDate().getTime();
        long endAt = goodsVo.getEndDate().getTime();
        long now = System.currentTimeMillis();

        int miaoshaStatus = 0;//秒杀状态，0未开始，1进行中，2已结束
        int remainSeconds = 0;//还剩 remainSeconds秒 开始秒杀

        /*
        * 1.秒杀还没开始，倒计时
        * 2.秒杀已结束，
        * 3.秒杀正在进行中
        * */
        if(now < startAt){//秒杀还没开始，倒计时
            miaoshaStatus = 0;
            remainSeconds = (int) ((startAt - now) / 1000);
        }else if( now > endAt){//秒杀已结束，
            miaoshaStatus = 2;
            remainSeconds = -1;
        }else{ //秒杀正在进行中
            miaoshaStatus = 1;
            remainSeconds = 0;
        }

        //返回秒杀状态，剩余时间
        model.addAttribute("miaoshaStatus",miaoshaStatus);
        model.addAttribute("remainSeconds",remainSeconds);

        System.out.println("商品详情查看：" + miaoshaUser.getNickname());
        return "goods_detail";
    }



}










