package com.sast.sastreadtrack.entity;

/**
 * 书籍实体类
 * 属性：id, 用户id, 书名, 作者, 总页数, 已读页数, 阅读状态（未读/阅读中/已读）
 */
public class Book {
    private Long id;
    private Long userId;
    private String title;
    private String author;
    private Integer totalPages;
    private Integer readPages;
    private String status; // 未读/阅读中/已读
    // getter/setter 省略
}

