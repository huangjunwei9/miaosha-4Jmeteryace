package com.imooc.miaosha.domain;

import java.math.BigDecimal;

/**
 id	bigint	20	0	0	-1	0	0	0		0	商品ID				-1	0
 goods_name	varchar	16	0	-1	0	0	0	0		0	商品名称	utf8	utf8_general_ci		0	0
 goods_title	varchar	64	0	-1	0	0	0	0		0	商品标题	utf8	utf8_general_ci		0	0
 goods_img	varchar	64	0	-1	0	0	0	0		0	商品图片	utf8	utf8_general_ci		0	0
 goods_detail	longtext	0	0	-1	0	0	0	0		0	商品详情介绍	utf8	utf8_general_ci		0	0
 goods_price	decimal	10	2	-1	0	0	0	0	0.00	0	商品价格				0	0
 goods_stock	int	11	0	-1	0	0	0	0	0	0	商品库存，-1表示没有限制				0	0
 */
// 商品表
public class Goods {
    private Long id;
    private String goodsName;
    private String goodsTitle;
    private String goodsImg;
    private String goodsDetail;
    private BigDecimal goodsPrice; //注意，这是商品的价格，关于钱，最好使用BigDecimal这种标准精度，它可以对超过16位有效位的数进行精确的运算
    private Integer goodsStock;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsTitle() {
        return goodsTitle;
    }

    public void setGoodsTitle(String goodsTitle) {
        this.goodsTitle = goodsTitle;
    }

    public String getGoodsImg() {
        return goodsImg;
    }

    public void setGoodsImg(String goodsImg) {
        this.goodsImg = goodsImg;
    }

    public String getGoodsDetail() {
        return goodsDetail;
    }

    public void setGoodsDetail(String goodsDetail) {
        this.goodsDetail = goodsDetail;
    }

    public BigDecimal getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(BigDecimal goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public Integer getGoodsStock() {
        return goodsStock;
    }

    public void setGoodsStock(Integer goodsStock) {
        this.goodsStock = goodsStock;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "id=" + id +
                ", goodsName='" + goodsName + '\'' +
                ", goodsTitle='" + goodsTitle + '\'' +
                ", goodsImg='" + goodsImg + '\'' +
                ", goodsDetail='" + goodsDetail + '\'' +
                ", goodsPrice=" + goodsPrice +
                ", goodsStock=" + goodsStock +
                '}';
    }
}


