package com.imooc.miaosha.domain;

import java.math.BigDecimal;
import java.util.Date;

/**
 id	bigint	20	0	0	-1	0	0	0		0	秒杀的商品表				-1	0
 goods_id	bigint	20	0	-1	0	0	0	0		0	商品id				0	0
 miaosha_price	decimal	10	2	-1	0	0	0	0	0.00	0	秒杀价				0	0
 stock_count	int	11	0	-1	0	0	0	0		0	供秒杀的库存数量				0	0
 start_date	datetime	0	0	-1	0	0	0	0		0	秒杀开始时间				0	0
 end_date	datetime	0	0	-1	0	0	0	0		0	秒杀结束时间				0	0
 * */
// 秒杀商品表
public class MiaoshaGoods {
    private Long id;
    private Long goodsId;
    private BigDecimal miaoshaPrice;
    private Integer stockCount;
    private Date startDate;
    private Date endDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public BigDecimal getMiaoshaPrice() {
        return miaoshaPrice;
    }

    public void setMiaoshaPrice(BigDecimal miaoshaPrice) {
        this.miaoshaPrice = miaoshaPrice;
    }

    public Integer getStockCount() {
        return stockCount;
    }

    public void setStockCount(Integer stockCount) {
        this.stockCount = stockCount;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "MiaoshaGoods{" +
                "id=" + id +
                ", goodsId=" + goodsId +
                ", miaoshaPrice=" + miaoshaPrice +
                ", stockCount=" + stockCount +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
