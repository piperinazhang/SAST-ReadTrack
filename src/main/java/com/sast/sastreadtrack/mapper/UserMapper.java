package com.sast.sastreadtrack.mapper;

import com.sast.sastreadtrack.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * 用户数据访问层
 */
@Mapper
public interface UserMapper {
    /**
     * 新增用户
     */
    int insert(User user);

    /**
     * 根据id查询用户
     */
    User selectById(Long id);

    /**
     * 这里新增一个根据用户名查询用户的数据库访问操作，方便校验用户是否存在，为了统一标准也使用xml文件映射
     */
    User selectByUsername(String username);
}
