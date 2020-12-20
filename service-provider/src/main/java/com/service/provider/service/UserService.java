package com.service.provider.service;

import com.service.provider.dao.UserMapper;
import com.service.provider.pojo.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService {
    @Resource
    private UserMapper userMapper;


    public User queryById(Long id){
        return this.userMapper.selectByPrimaryKey(id);
    }
}
