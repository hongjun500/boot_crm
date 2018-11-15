package com.dnsoft.bootcrm.core.service;

import com.dnsoft.bootcrm.core.pojo.User;

public interface UserService {
    public User findUser(String usercode,String passwrod);
}
