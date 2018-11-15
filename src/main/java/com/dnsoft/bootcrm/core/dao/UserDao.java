package com.dnsoft.bootcrm.core.dao;

import com.dnsoft.bootcrm.core.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserDao {
    public User findUser(@Param("usercode") String usercode,
                         @Param("password") String password);
}
