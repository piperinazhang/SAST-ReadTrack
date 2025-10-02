package com.sast.sastreadtrack.controller;

import com.sast.sastreadtrack.entity.User;
import com.sast.sastreadtrack.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.nio.file.Files;
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
        /**
         * Map表示键值对，ResponseEntity是Spring提供的一个类，用于封装HTTP相应信息
         * 这里的Map就作为响应体来使用，同时用try-catch进行异常捕获，和Service层联动保证处理异常情况
         */
        Map<String, Object> result = new HashMap<>();
        try {
            // 这里若检测到用户名已存在则抛出异常并提示
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
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> loginForm, HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        try {
            String username = loginForm.get("username");
            String password = loginForm.get("password");
            // 这里若用户名不存在或密码错误，则抛出异常
            User user = userService.login(username, password);
            // 简单 Session 存储
            session.setAttribute("user", user);
            result.put("success", true);
            result.put("message", "登录成功");
            result.put("user", user);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(result);
        }
    }
}
