package com.imooc.miaosha.vo;

import com.imooc.miaosha.domain.Goods;

import java.math.BigDecimal;
import java.util.Date;

public class GoodsVo extends Goods {
    //把 MiaoshaGoods表里的信息 传过来（秒杀价、秒杀库存、秒杀商品的开始/结束时间）
    private BigDecimal miaoshaPrice;
    private Integer stockCount;
    private Date startDate;
    private Date endDate;

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
        return "GoodsVo{" +
                "miaoshaPrice=" + miaoshaPrice +
                ", stockCount=" + stockCount +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
