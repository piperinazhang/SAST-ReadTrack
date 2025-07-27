package com.sast.sastreadtrack.service;

import com.sast.sastreadtrack.entity.User;
import org.springframework.stereotype.Service;

/**
 * 用户服务接口
 */
@Service
public interface UserService {
    /**
     * 用户注册
     * @param user 用户信息（包含姓名、密码）
     * @return 注册成功返回true
     */
    boolean register(User user);

    /**
     * 用户登录
     * @param username 用户名
     * @param password 密码
     * @return 登录成功返回用户信息
     */
    User login(String username, String password);

    /**
     * 根据id获取用户信息
     */
    User getUserById(Long id);
}
