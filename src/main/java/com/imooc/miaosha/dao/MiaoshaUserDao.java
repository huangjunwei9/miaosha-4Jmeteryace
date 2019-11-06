package com.imooc.miaosha.dao;

import com.imooc.miaosha.domain.MiaoshaUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MiaoshaUserDao {

    //根据手机号获取MiaoshaUser
    @Select("select * from miaosha_user where id=#{id}")
    public MiaoshaUser getById(@Param("id") long id);



}
