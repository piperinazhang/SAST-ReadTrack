-- 这是一个定义数据库的文件:)
-- 创建数据库
CREATE DATABASE IF NOT EXISTS sast_readtrack DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE sast_readtrack;

-- 用户表
CREATE TABLE IF NOT EXISTS `user` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(50) NOT NULL COMMENT '姓名',
    `student_id` VARCHAR(20) NOT NULL COMMENT '学号',
    `password` VARCHAR(100) NOT NULL COMMENT '密码',
    `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_student_id` (`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- 书籍表
CREATE TABLE IF NOT EXISTS `book` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `user_id` BIGINT NOT NULL COMMENT '用户ID',
    `title` VARCHAR(100) NOT NULL COMMENT '书名',
    `author` VARCHAR(50) NOT NULL COMMENT '作者',
    `total_pages` INT NOT NULL COMMENT '总页数',
    `read_pages` INT NOT NULL DEFAULT 0 COMMENT '已读页数',
    `status` VARCHAR(20) NOT NULL DEFAULT '未读' COMMENT '阅读状态：未读/阅读中/已读',
    `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    KEY `idx_user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='书籍表';

-- 添加外键约束
ALTER TABLE `book`
ADD CONSTRAINT `fk_book_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE;
