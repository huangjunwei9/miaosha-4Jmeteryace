package com.imooc.miaosha.domain;


import java.math.BigDecimal;
import java.util.Date;

/**
 id	bigint	20	0	0	-1	0	0	0		0					-1	0
 user_id	bigint	20	0	-1	0	0	0	0		0	用户ID				0	0
 goods_id	bigint	20	0	-1	0	0	0	0		0	商品ID				0	0
 delivery_addr_id	bigint	20	0	-1	0	0	0	0		0	收货地址ID				0	0
 goods_name	varchar	16	0	-1	0	0	0	0		0	冗余过来的商品名称，方便订单列表显示，而不必关联goods表	utf8	utf8_general_ci		0	0
 goods_count	int	11	0	-1	0	0	0	0	0	0	下单的商品数量				0	0
 goods_price	decimal	10	2	-1	0	0	0	0	0.00	0	商品单价				0	0
 order_channel	tinyint	4	0	-1	0	0	0	0	0	0	订单的渠道：1表示用PC下的单，2Android，3IOS				0	0
 status	tinyint	4	0	-1	0	0	0	0	0	0	订单状态，0新建未支付，1已支付，2已发货，3已收货，4已退款，5已完成				0	0
 create_date	datetime	0	0	-1	0	0	0	0		0	订单的创建时间				0	0
 pay_date	datetime	0	0	-1	0	0	0	0		0	订单支付时间				0	0
 */
// 订单信息
public class OrderInfo {
    private Long id;
    private Long userId;
    private Long goodsId;
    private Long deliveryAddrId;
    private String goodsName;
    private Integer goodsCount;
    private BigDecimal goodsPrice;
    private Integer orderChannel;
    private Integer status; //订单状态
    private Date createDate; //订单的创建时间
    private Date payDate;

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

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Long getDeliveryAddrId() {
        return deliveryAddrId;
    }

    public void setDeliveryAddrId(Long deliveryAddrId) {
        this.deliveryAddrId = deliveryAddrId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Integer getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(Integer goodsCount) {
        this.goodsCount = goodsCount;
    }

    public BigDecimal getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(BigDecimal goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public Integer getOrderChannel() {
        return orderChannel;
    }

    public void setOrderChannel(Integer orderChannel) {
        this.orderChannel = orderChannel;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    @Override
    public String toString() {
        return "OrderInfo{" +
                "id=" + id +
                ", userId=" + userId +
                ", goodsId=" + goodsId +
                ", deliveryAddrId=" + deliveryAddrId +
                ", goodsName='" + goodsName + '\'' +
                ", goodsCount=" + goodsCount +
                ", goodsPrice=" + goodsPrice +
                ", orderChannel=" + orderChannel +
                ", status=" + status +
                ", createDate=" + createDate +
                ", payDate=" + payDate +
                '}';
    }
}
























