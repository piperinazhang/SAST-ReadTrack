package com.sast.sastreadtrack.entity;

import java.util.Date;

/**
 * 书籍实体类
 * 属性：id, 用户id, 书名, 作者, 总页数, 已读页数, 阅读状态（未读/阅读中/已读）
 */
public class Book {
    private Long id;               // 对应数据库int类型
    private Long userId;           // 对应数据库user_id字段
    private String title;             // 书名
    private String author;            // 作者
    private Integer totalPages;       // 总页数，对应数据库total_pages
    private Integer currentPage;      // 当前页数，对应数据库current_page
    private Date createdAt;           // 创建时间，对应数据库created_at
    private String status;            // 阅读状态（未读/阅读中/已读）
    // getter/setter 省略
}

