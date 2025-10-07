package com.sast.sastreadtrack.controller;

import com.sast.sastreadtrack.entity.Book;
import com.sast.sastreadtrack.service.BookService;
import com.sast.sastreadtrack.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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
     * 从 Authorization Header 获取用户ID
     */
    private Long getUserIdFromToken(String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new RuntimeException("缺少或无效的 Token");
        }
        String token = authHeader.substring(7);
        return JwtUtil.getUserIdFromToken(token);
    }

    /**
     * 添加新书籍
     * @param book 书籍信息（包含书名、作者、总页数）
     */
    @PostMapping("/add")
    public ResponseEntity<Map<String, Object>> addBook(@RequestBody Book book,
                                                       @RequestHeader("Authorization") String authHeader) {
        Map<String, Object> result = new HashMap<>();
        try {
            Long userId = getUserIdFromToken(authHeader);
            book.setUserId(userId);
            boolean success = bookService.addBook(book,userId);
            if (success) {
                result.put("success", true);
                result.put("message", "书籍添加成功");
            }
            else{
                result.put("success", false);
                result.put("message", "书籍添加失败，请重试");
            }
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(result);
        }
    }

    /**
     * 更新阅读进度
     * @param bookId 书籍ID
     * @param currentPages 已读页数
     */
    @PutMapping("/{bookId}/progress")
    public ResponseEntity<Map<String, Object>> updateProgress(
            @PathVariable Long bookId,
            @RequestParam Integer currentPages,
            @RequestHeader("Authorization") String authHeader) {
        Map<String, Object> result = new HashMap<>();
        try {
            Long userId = getUserIdFromToken(authHeader);
            boolean success = bookService.updateReadProgress(bookId, currentPages, userId);
            if (success) {
                result.put("success", true);
                result.put("message", "进度更新成功");
            }
            else{
                result.put("success", false);
                result.put("message", "进度更新失败，请重试");
            }
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(result);
        }

    }

    /**
     * 更新阅读状态
     * @param bookId 书籍ID
     * @param status 阅读状态（未读/阅读中/已读）
     */
    @PutMapping("/{bookId}/status")
    public ResponseEntity<Map<String, Object>> updateStatus(
            @PathVariable Long bookId,
            @RequestParam String status,
            @RequestHeader("Authorization") String authHeader) {
        Map<String, Object> result = new HashMap<>();
        try {
            Long userId = getUserIdFromToken(authHeader);
            boolean success = bookService.updateStatus(bookId, status, userId);
            if (success) {
                result.put("success", true);
                result.put("message", "状态更新成功");
            }else{
                result.put("success", false);
                result.put("message","状态更新失败，请重试");
            }
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(result);
        }

    }

    /**
     * 删除书籍（仅能删除自己的书籍）
     * @param bookId 书籍ID
     */
    @DeleteMapping("/{bookId}")
    public ResponseEntity<Map<String, Object>> deleteBook(@PathVariable Long bookId,
                                                          @RequestHeader("Authorization") String authHeader) {
        Map<String, Object> result = new HashMap<>();
        try {
            Long userId = getUserIdFromToken(authHeader);
            boolean success = bookService.deleteBook(bookId, userId);
            if (success) {
                result.put("success", true);
                result.put("message", "删除成功");
            }else{
                result.put("success", false);
                result.put("message","删除失败，请重试");
            }
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(result);
        }

    }

    /**
     * 查询用户的书籍列表
     * @param status 阅读状态（可选）
     * 需要分页查询
     */
    @GetMapping("/list")
    public ResponseEntity<List<Book>> getBooks(@RequestParam(required = false) String status,
                                               @RequestParam(defaultValue = "1") int pageNum,
                                               @RequestParam(defaultValue = "10") int pageSize,
                                               @RequestHeader("Authorization") String authHeader) {
        try {
            Long userId = getUserIdFromToken(authHeader);
            if (status == null) {
                return ResponseEntity.ok(bookService.getUserBooks(userId, pageNum, pageSize));
            } else {
                return ResponseEntity.ok(bookService.getBooksByStatus(userId, status, pageNum, pageSize));
            }
        }catch (Exception e){
            return ResponseEntity.badRequest().body(null);
        }

    }

    /**
     * 模糊查询书籍
     * @param keyword 关键词
     * 这里传进来的keyword可以是作者名，也可以是书籍名，且都做了模糊查询处理
     */
    @GetMapping("/search")
    public ResponseEntity<List<Book>> searchBooks(@RequestParam String keyword,
                                                  @RequestHeader("Authorization") String authHeader) {
        try {
            Long userId = getUserIdFromToken(authHeader);
            List<Book> books = bookService.searchBooks(keyword, userId);
            return ResponseEntity.ok(books);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(null);
        }

    }

    /**
     * 获取阅读统计信息
     */
    @GetMapping("/stats")
    public ResponseEntity<Map<String, Object>> getReadingStats(@RequestHeader("Authorization") String authHeader) {
        Map<String, Object> result = new HashMap<>();
        try {
            Long userId = getUserIdFromToken(authHeader);
            List<Book> books = bookService.getReadingStats(userId);
            result.put("success", true);
            result.put("message", "已经查询到当前用户的书籍阅读信息");
            result.put("books", books);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
