package com.imooc.miaosha.dao;

import com.imooc.miaosha.domain.MiaoshaOrder;
import com.imooc.miaosha.domain.OrderInfo;
import org.apache.ibatis.annotations.*;

//定义接口
@Mapper
public interface OrderDao {

    //根据用户ID和商品Id，获取miaosha_order表中的一个订单
    @Select("select * from miaosha_order where user_id = #{userId} and goods_id = #{goodsId}")
    public MiaoshaOrder getMiaoshaOrderByUserIdGoodsId(@Param("userId") long userId, @Param("goodsId") long goodsId);

    //在订单表order_info中插入订单，并返回订单id
    @Insert("insert into order_info (user_id, goods_id, delivery_addr_id, goods_name, goods_count, goods_price, order_channel, status, create_date) " +
            "values (#{userId}, #{goodsId}, #{deliveryAddrId}, #{goodsName}, #{goodsCount}, #{goodsPrice}, #{orderChannel}, #{status}, #{createDate})")
    @SelectKey(keyColumn="id", keyProperty="id", resultType=long.class, before = false, statement = "select last_insert_id()")
    public long insert(OrderInfo orderInfo);

    //在秒杀订单表miaosha_order中插入订单
    @Insert("insert into miaosha_order (user_id, order_id, goods_id) " +
            "values (#{userId}, #{orderId}, #{goodsId})")
    public void insertMiaoshaOrder(MiaoshaOrder miaoshaOrder);
}
