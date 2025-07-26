-- 这是一个定义数据库的文件:)
-- 创建数据库
CREATE DATABASE IF NOT EXISTS sast_readtrack DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE sast_readtrack;

-- 用户表
CREATE TABLE user (
                      id LONG PRIMARY KEY AUTO_INCREMENT,
                      username VARCHAR(50) UNIQUE NOT NULL,
                      password VARCHAR(50) NOT NULL,
                      created_at DATETIME
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 书籍表
CREATE TABLE book (
                      id LONG PRIMARY KEY AUTO_INCREMENT,
                      title VARCHAR(100) NOT NULL,
                      author VARCHAR(50),
                      total_pages INT NOT NULL,
                      current_page INT DEFAULT 0,
                      user_id LONG,
                      created_at DATETIME
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 添加外键约束
ALTER TABLE `book`
ADD CONSTRAINT `fk_book_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE;
