package com.sast.sastreadtrack.controller;

import com.sast.sastreadtrack.entity.User;
import com.sast.sastreadtrack.service.UserService;
import com.sast.sastreadtrack.util.JwtUtil;
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
     * @param user 用户信息（包含 、姓名、密码）
     */
    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(@RequestBody User user) {
        Map<String, Object> result = new HashMap<>();
        try {
            boolean success = userService.register(user);
            result.put("success", success);
            result.put("message", "注册成功");
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(result);
        }
    }

    /**
     * 用户登录
     * @param loginForm 登录表单（包含 、密码）
     */
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> loginForm) {
        Map<String, Object> result = new HashMap<>();
        try {
            String username = loginForm.get("username");
            String password = loginForm.get("password");
            User user = userService.login(username, password);

            // 生成 JWT Token
            String token = JwtUtil.generateToken(user.getId(), user.getUsername());

            result.put("success", true);
            result.put("message", "登录成功");
            result.put("token", token);
            result.put("user", user);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(result);
        }
    }
}
