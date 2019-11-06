package com.imooc.miaosha.service;

import com.imooc.miaosha.domain.MiaoshaUser;
import com.imooc.miaosha.domain.OrderInfo;
import com.imooc.miaosha.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
public class MiaoshaService {

    @Autowired
    GoodsService goodsService;

    @Autowired
    OrderService orderService;


    /* 执行商品秒杀
     * 1.减少库存
     * 2.下订单
     * 3.将订单写入秒杀订单miaosha_order，
     * 4.秒杀成功则返回秒杀订单
     * 注意：这应该放入一个事务中进行，任何一步失败都不能执行商品秒杀
     * */
    @Transactional
    public OrderInfo miaosha(MiaoshaUser miaoshaUser, GoodsVo goodsVo) {
        //减库存，对goods表中的商品id 进行减1操作
        goodsService.reduceStock(goodsVo);

        //下订单，并写入到秒杀订单miaosha_order中
        return orderService.createOrder(miaoshaUser, goodsVo);
    }
}












