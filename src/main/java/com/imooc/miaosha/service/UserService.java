package com.imooc.miaosha.service;

import com.imooc.miaosha.dao.UserDao;
import com.imooc.miaosha.domain.MiaoshaUser;
import com.imooc.miaosha.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    UserDao userDao;

    //根据id获取用户
    public User getById(int id){
        return userDao.getById(id);
    }

    //事务，向数据库中插入User(id = 2, name = 222)
    @Transactional
    public boolean tx() {
        User u1 = new User();
        u1.setId(4);
        u1.setName("222");
        userDao.insert(u1);

        User u2 = new User();
        u2.setId(3);
        u2.setName("111");
        userDao.insert(u2);

        return  true;
    }




}
