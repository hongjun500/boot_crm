package com.dnsoft.bootcrm.core.service;

import com.dnsoft.bootcrm.core.dao.UserDao;
import com.dnsoft.bootcrm.core.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("userService")
@Transactional//事务管理
public class UserServiceImpl implements UserService {
    //注入UserDao
     @Autowired
     private UserDao userDao;
     @Override
     public User findUser(String usercode,String password){
         User user=this.userDao.findUser(usercode,password);
         return user;
     }
}
