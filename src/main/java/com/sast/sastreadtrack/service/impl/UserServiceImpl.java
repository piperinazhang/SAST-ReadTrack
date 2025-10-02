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
        // 校验用户名是否存在，如果存在，直接抛出异常给Controller层
        User existing = userMapper.selectByUsername(user.getUsername());
        if (existing != null) {
            throw new RuntimeException("用户名已存在");
        }

        /**
         * 这里的rows用了SQL语言insert操作的一个特性：
         * 如果插入成功则返回受操作的行数，在这里就是1
         * 如果插入失败则返回0
         * 因此可以直接通过判断rows的数值来覆盖注册成功或失败两个逻辑
         */

        int rows = userMapper.insert(user);
        if (rows <= 0) {
            throw new RuntimeException("抱歉，注册失败，请重试");
        }
        return true;
    }

    @Override
    public User login(String username, String password) {
        User user = userMapper.selectByUsername(username);
        if (user == null) {
            throw new RuntimeException("用户不存在");
        }
        if (!user.getPassword().equals(password)){
            throw new RuntimeException("密码错误，请重试");
        }
        return user;
    }

    @Override
    public User getUserById(Long id) {
        return userMapper.selectById(id);
    }
}
