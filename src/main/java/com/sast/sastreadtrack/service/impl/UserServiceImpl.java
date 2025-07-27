package com.sast.sastreadtrack.service.impl;

import com.sast.sastreadtrack.entity.User;
import com.sast.sastreadtrack.mapper.UserMapper;
import com.sast.sastreadtrack.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;

/**
 * 用户服务实现类
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    @Transactional
    public boolean register(User user) {
        //用户注册
    }

    @Override
    public User login(String username, String password) {
        //用户登录
    }

    @Override
    public User getUserById(Long id) {
        //根据id获取用户信息
    }
}
