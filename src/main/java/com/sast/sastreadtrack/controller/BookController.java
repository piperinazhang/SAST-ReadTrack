package com.sast.sastreadtrack.controller;

import com.sast.sastreadtrack.entity.Book;
import com.sast.sastreadtrack.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 书籍控制器
 * 处理书籍的CRUD、进度更新、查询等请求
 */
@RestController
@RequestMapping("/api/book")
public class BookController {
    @Autowired
    private BookService bookService;

    /**
     * 添加新书籍
     * @param book 书籍信息（包含书名、作者、总页数）
     */
    @PostMapping("/add")
    public ResponseEntity<Map<String, Object>> addBook(@RequestBody Book book) {
        // TODO: 实现添加书籍逻辑
        return null;
    }

    /**
     * 更新阅读进度
     * @param bookId 书籍ID
     * @param readPages 已读页数
     */
    @PutMapping("/{bookId}/progress")
    public ResponseEntity<Map<String, Object>> updateProgress(
            @PathVariable Long bookId,
            @RequestParam Integer readPages) {
        // TODO: 实现更新进度逻辑
        return null;
    }

    /**
     * 删除书籍（仅能删除自己的书籍）
     * @param bookId 书籍ID
     */
    @DeleteMapping("/{bookId}")
    public ResponseEntity<Map<String, Object>> deleteBook(@PathVariable Long bookId) {
        // TODO: 实现删除书籍逻辑
        return null;
    }

    /**
     * 查询用户的书籍列表
     * @param status 阅读状态（可选）
     */
    @GetMapping("/list")
    public ResponseEntity<List<Book>> getBooks(@RequestParam(required = false) String status) {
        // TODO: 实现查询书籍逻辑
        return null;
    }

    /**
     * 模糊查询书籍
     * @param keyword 关键词
     */
    @GetMapping("/search")
    public ResponseEntity<List<Book>> searchBooks(@RequestParam String keyword) {
        // TODO: 实现模糊查询逻辑
        return null;
    }

    /**
     * 获取阅读统计信息
     */
    @GetMapping("/stats")
    public ResponseEntity<Map<String, Object>> getReadingStats() {
        // TODO: 实现阅读统计逻辑
        return null;
    }
}
