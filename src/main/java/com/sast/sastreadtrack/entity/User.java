package com.sast.sastreadtrack.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 用户实体类
 * 属性：id, 姓名,  , 密码（加密存储）
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;
    private String username;
    private String password;
    private Date createdAt;           // 创建时间，对应数据库created_at

    // getter/setter 省略
}
