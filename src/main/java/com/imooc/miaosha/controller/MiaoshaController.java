package com.imooc.miaosha.controller;

import com.imooc.miaosha.domain.MiaoshaOrder;
import com.imooc.miaosha.domain.MiaoshaUser;
import com.imooc.miaosha.domain.OrderInfo;
import com.imooc.miaosha.result.CodeMsg;
import com.imooc.miaosha.service.GoodsService;
import com.imooc.miaosha.service.MiaoshaService;
import com.imooc.miaosha.service.OrderService;
import com.imooc.miaosha.vo.GoodsVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/* 秒杀进行中，所使用的类 */
@Controller
@RequestMapping(value="/miaosha")
public class MiaoshaController {

    @Autowired
    GoodsService goodsService;

    @Autowired
    OrderService orderService;

    @Autowired
    MiaoshaService miaoshaService;

    private static Logger log = LoggerFactory.getLogger(GoodsController.class);//


    //响应“立即秒杀”
    @RequestMapping("/do_miaosha")
    public String toGoodsList(Model model, MiaoshaUser miaoshaUser, @RequestParam("goodsId")long goodsId){
        model.addAttribute("user", miaoshaUser);

        if(miaoshaUser == null){
            return "login";
        }

        //按照商品id获取商品的库存，判断库存是否符合用户需求
        GoodsVo goodsVo = goodsService.getGoodsVoByGoodsId(goodsId);
        int stockCount = goodsVo.getStockCount();
        if(stockCount <= 0){
            model.addAttribute("errmsg", CodeMsg.MIAO_SHA_NO_STOCK.getMsg());//返回信息“库存不足”
            return "miaosha_fail";
        }

        /* 判断是否已经秒杀到了，用于防止一个人秒杀多个商品
        * 1.根据用户ID和商品Id，获取秒杀订单
        * 2.如果秒杀订单不是空，说明用户已经秒杀过了，返回错误信息“重复秒杀”
        * 3.如果商品有库存，而且用户并没有秒杀过，则可执行商品秒杀
        * */
        MiaoshaOrder miaoshaOrder = orderService.getMiaoshaOrderByUserIdGoodsId(miaoshaUser.getId(), goodsId);
        if(miaoshaOrder != null){ //如果秒杀订单不是空，说明用户已经秒杀过了，返回错误信息“重复秒杀”
            model.addAttribute("errmsg", CodeMsg.MIAO_SHA_REPEAT.getMsg());
            return "miaosha_fail";
        }

        /* 执行商品秒杀
        * 1.减少库存
        * 2.下订单
        * 3.将订单写入秒杀订单miaosha_order，
        * 4.秒杀成功则返回秒杀订单
        * 注意：这应该放入一个事务中进行，任何一步失败都不能执行商品秒杀
        * */
        OrderInfo orderInfo = miaoshaService.miaosha(miaoshaUser, goodsVo);
        model.addAttribute("orderInfo", orderInfo); //返回订单所有信息
        model.addAttribute("goods", goodsVo);       //返回商品所有信息
        return "order_detail";
    }





}


