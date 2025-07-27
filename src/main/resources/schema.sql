-- 这是一个定义数据库的文件:)
-- 创建数据库
CREATE DATABASE IF NOT EXISTS sast_readtrack DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE sast_readtrack;

-- 用户表（避免关键字冲突）
CREATE TABLE t_user (
                        id BIGINT PRIMARY KEY AUTO_INCREMENT,
                        student_id VARCHAR(20) UNIQUE NOT NULL,  -- 学号
                        username VARCHAR(50) UNIQUE NOT NULL,
                        password VARCHAR(100) NOT NULL,  -- 适配加密后密码
                        created_at DATETIME DEFAULT CURRENT_TIMESTAMP  -- 默认当前时间
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 书籍表
CREATE TABLE book (
                      id BIGINT PRIMARY KEY AUTO_INCREMENT,
                      title VARCHAR(100) NOT NULL,
                      author VARCHAR(50),
                      total_pages INT NOT NULL,
                      current_page INT DEFAULT 0,
                      user_id BIGINT,
                      created_at DATETIME DEFAULT CURRENT_TIMESTAMP,  -- 默认当前时间
                      status ENUM('0', '1', '2') NOT NULL DEFAULT '0',  -- 阅读状态:0未读 1阅读中 2已读
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


