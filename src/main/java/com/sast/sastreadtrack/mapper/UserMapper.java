package com.sast.sastreadtrack.mapper;

import com.sast.sastreadtrack.entity.User;
import org.apache.ibatis.annotations.Mapper;

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
}
