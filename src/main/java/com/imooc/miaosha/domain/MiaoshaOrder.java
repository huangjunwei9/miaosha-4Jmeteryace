package com.imooc.miaosha.domain;

/**
 id	bigint	20	0	0	-1	0	0	0		0					-1	0
 user_id	bigint	20	0	-1	0	0	0	0		0	用户ID				0	0
 order_id	bigint	20	0	-1	0	0	0	0		0	订单ID				0	0
 goods_id	bigint	20	0	-1	0	0	0	0		0	商品ID				0	0
 * */
// 秒杀订单
public class MiaoshaOrder {
    private Long id;
    private Long userId;
    private Long orderId;
    private Long goodsId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    @Override
    public String toString() {
        return "MiaoshaOrder{" +
                "id=" + id +
                ", userId=" + userId +
                ", orderId=" + orderId +
                ", goodsId=" + goodsId +
                '}';
    }
}
