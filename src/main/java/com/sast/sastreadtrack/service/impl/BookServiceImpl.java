package com.sast.sastreadtrack.service.impl;

import com.github.pagehelper.PageHelper;
import com.sast.sastreadtrack.entity.Book;
import com.sast.sastreadtrack.mapper.BookMapper;
import com.sast.sastreadtrack.service.BookService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

import static java.util.Set.of;

/**
 * 书籍服务层实现类
 */
@Service
public class BookServiceImpl implements BookService {

    @Resource
    private BookMapper bookMapper;
    // 定义set集合，方便下面的状态查询
    private static final Set<String> VALID_STATUS = of("0", "1", "2");

    @Override
    @Transactional
    public boolean addBook(Book book, Long userId) {
        if (userId == null) {
            throw new RuntimeException("请登录并重试");
        }
        // 书名不能为null或者空字符串
        if (book.getTitle() == null || book.getTitle().trim().isEmpty()) {
            throw new RuntimeException("书名不能为空");
        }

        if (book.getTotalPages() == null || book.getTotalPages() <= 0) {
            throw new RuntimeException("总页数必须大于0");
        }
        // 默认未读和页数为0
        book.setCurrentPage(0);
        book.setStatus(0);
        book.setUserId(userId);
        return bookMapper.insert(book) > 0;
    }

    @Override
    @Transactional
    public boolean updateReadProgress(Long bookId, Integer currentPage, Long userId) {
        if (userId == null) {
            throw new RuntimeException("请登录并重试");
        }

        if (currentPage == null || currentPage < 0) {
            throw new RuntimeException("页数不合法，请重试");
        }

        // 查询该用户的书籍
        Book searchBook = bookMapper.selectByIdAndUserId(bookId, userId);
        if (searchBook == null) {
            throw new RuntimeException("抱歉，该书籍不存在，请重试");
        }

        // 更新进度
        searchBook.setCurrentPage(currentPage);

        // 根据进度自动设置状态
        if (currentPage == 0) {
            searchBook.setStatus(0); // 未读
        } else if (currentPage >= searchBook.getTotalPages()) {
            searchBook.setStatus(2); // 已读
        } else {
            searchBook.setStatus(1); // 阅读中
        }

        // 调用 Mapper 更新
        return bookMapper.updateReadProgress(searchBook) > 0;
    }



    @Override
    @Transactional
    public boolean updateStatus(Long bookId, String status, Long userId) {
        if (userId == null) {
            throw new RuntimeException("请登录并重试");
        }
        if (!VALID_STATUS.contains(status)) {
            throw new RuntimeException("阅读状态非法，只能是 0（未读）、1（阅读中）、2（已读），请重试");
        }
        Book searchBook = bookMapper.selectByIdAndUserId(bookId, userId);
        if (searchBook == null){
            throw new RuntimeException("抱歉，该书籍不存在，请重试");
        }
        // 更新状态
        int Intstatus = Integer.parseInt(status);
        searchBook.setStatus(Intstatus);
        // 根据状态更新阅读进度
        if (Intstatus == 0) {
            searchBook.setCurrentPage(0);
        }else if (Intstatus == 2) {
            searchBook.setCurrentPage(searchBook.getTotalPages());
        }else{
            throw new RuntimeException("仅可输入0或者2，其它为无效输入，请重试");
        }

        return bookMapper.updateStatus(searchBook) > 0;
    }

    @Override
    @Transactional
    public boolean deleteBook(Long bookId, Long userId) {
        if (userId == null) {
            throw new RuntimeException("请登录并重试");
        }
        Book searchBook = bookMapper.selectByIdAndUserId(bookId, userId);
        if (searchBook == null){
            throw new RuntimeException("抱歉，该书籍不存在，请重试");
        }
        return bookMapper.deleteById(bookId, userId) > 0;
    }

    @Override
    public List<Book> getUserBooks(Long userId,int pageNum, int pageSize) {
        if (userId == null) {
           throw new RuntimeException("请登录并重试");
        }
        PageHelper.startPage(pageNum, pageSize);
        return bookMapper.selectByUserId(userId);
    }

    @Override
    public List<Book> getBooksByStatus(Long userId, String status,int pageNum, int pageSize) {
        if (userId == null) {
            throw new RuntimeException("请登录并重试");
        }
        if (!VALID_STATUS.contains(status)) {
            throw new RuntimeException("阅读状态非法，只能是 0（未读）、1（阅读中）、2（已读），请重试");
        }
        PageHelper.startPage(pageNum, pageSize);
        return bookMapper.selectByUserIdAndStatus(userId, status);
    }

    @Override
    public List<Book> searchBooks(String keyword, Long userId) {
        if (userId == null) {
            throw new RuntimeException("请登录并重试");
        }
        return bookMapper.searchBooks(keyword, userId);
    }

    @Override
    public List<Book> getReadingStats(Long userId) {
        if (userId == null) {
            throw new RuntimeException("请登录后重试");
        }
        return bookMapper.getReadingStats(userId);
    }
}
