package com.sast.sastreadtrack.controller;

import com.sast.sastreadtrack.entity.User;
import com.sast.sastreadtrack.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户控制器
 * 处理用户注册、登录等请求
 */
@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 用户注册
     * @param user 用户信息（包含学号、姓名、密码）
     */
    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(@RequestBody User user) {
        // TODO: 实现注册逻辑

    }

    /**
     * 用户登录
     * @param loginForm 登录表单（包含学号、密码）
     */
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> loginForm) {
        // TODO: 实现登录逻辑

    }
}
