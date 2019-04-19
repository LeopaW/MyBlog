package com.xa.service.impl;

import com.xa.mapper.UserMapper;
import com.xa.pojo.User;
import com.xa.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public boolean checkLogin(User user) {

        user = userMapper.checkLogin(user);
        if(user == null){
            return false;
        }else{
            return true;
        }

    }
}
