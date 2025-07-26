package com.sast.sastreadtrack.mapper;

import com.sast.sastreadtrack.entity.Book;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
 * 书籍数据访问层
 */
@Mapper
public interface BookMapper {
    /**
     * 新增书籍
     */
    int insert(Book book);

    /**
     * 更新阅读进度
     */
    int updateReadProgress(Book book);

    /**
     * 更新阅读状态
     * @param book 包含 bookId（id）、userId、status 的实体类
     */
    int updateStatus(Book book);

    /**
     * 删除书籍
     */
    int deleteById(Long id, Long userId);

    /**
     * 查询用户的所有书籍
     */
    List<Book> selectByUserId(Long userId);

    /**
     * 根据阅读状态查询用户的书籍
     */
    List<Book> selectByUserIdAndStatus(Long userId, String status);

    /**
     * 模糊查询书籍
     */
    List<Book> searchBooks(String keyword, Long userId);

    /**
     * 获取用户的阅读统计
     */
    Book getReadingStats(Long userId);
}
