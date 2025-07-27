package com.sast.sastreadtrack.service.impl;

import com.sast.sastreadtrack.entity.Book;
import com.sast.sastreadtrack.mapper.BookMapper;
import com.sast.sastreadtrack.service.BookService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 书籍服务层实现类
 */
@Service
public class BookServiceImpl implements BookService {

    @Resource
    private BookMapper bookMapper;

    @Override
    @Transactional
    public boolean addBook(Book book) {
        //添加书籍
    }

    @Override
    @Transactional
    public boolean updateReadProgress(Long bookId, Integer currentPages, Long userId) {
        //更新阅读进度
    }

    @Override
    @Transactional
    public boolean updateStatus(Long bookId, String status, Long userId) {
        // 更新阅读状态
    }

    @Override
    @Transactional
    public boolean deleteBook(Long bookId, Long userId) {
        //删除书籍
    }

    @Override
    public List<Book> getUserBooks(Long userId) {
        //查询用户所有书籍
    }

    @Override
    public List<Book> getBooksByStatus(Long userId, String status) {
        //按阅读状态筛选书籍
    }

    @Override
    public List<Book> searchBooks(String keyword, Long userId) {
        //模糊查询书籍
    }

    @Override
    public Book getReadingStats(Long userId) {
        //获取阅读统计信息
    }
}
