package com.sast.sastreadtrack.service;

import com.sast.sastreadtrack.entity.Book;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 书籍服务接口
 */
@Service
public interface BookService {
    /**
     * 添加新书籍
     * @param book 书籍信息（包含书名、作者、总页数等）
     * @return 添加成功返回true
     */
    boolean addBook(Book book);

    /**
     * 更新阅读进度
     * @param bookId 书籍ID
     * @param currentPages 已读页数
     * @param userId 用户ID（用于验证权限）
     * @return 更新成功返回true
     */
    boolean updateReadProgress(Long bookId, Integer currentPages, Long userId);

    /**
     * 更新阅读状态
     * @param bookId 书籍ID
     * @param status 阅读状态（未读/阅读中/已读）
     * @param userId 用户ID（用于验证权限）
     * @return 更新成功返回true
     */
    boolean updateStatus(Long bookId, String status, Long userId);

    /**
     * 删除书籍（仅能删除自己的书籍）
     */
    boolean deleteBook(Long bookId, Long userId);

    /**
     * 查询用户的所有书籍
     */
    List<Book> getUserBooks(Long userId);

    /**
     * 按阅读状态筛选书籍
     */
    List<Book> getBooksByStatus(Long userId, String status);

    /**
     * 模糊查询书籍
     */
    List<Book> searchBooks(String keyword, Long userId);

    /**
     * 获取阅读统计信息
     * @return 包含总页数和已读页数的统计信息
     */
    Book getReadingStats(Long userId);
}
