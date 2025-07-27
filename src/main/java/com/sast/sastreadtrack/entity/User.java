package com.sast.sastreadtrack.entity;

import java.util.Date;

/**
 * 用户实体类
 * 属性：id, 姓名, 学号, 密码（加密存储）
 */
public class User {
    private Long id;
    private String username;
    private String studentId;
    private String password;
    private Date createdAt;           // 创建时间，对应数据库created_at

    // getter/setter 省略
}
