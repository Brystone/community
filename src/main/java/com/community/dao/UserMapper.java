package com.community.dao;

import com.community.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author stone
 * @version 1.0
 * @ClassName UserMapper
 * @Description
 * @date 2020/5/5 18:59
 */
@Mapper
public interface UserMapper {

    User selectById(int id);

    User selectByName(String username);

    User selectByEmail(String email);

    int insertUser(User user);

    int updateStatus(@Param("id") int id,  @Param("status") int status);

    int updateHeader(@Param("id") int id,  @Param("headerUrl") String headerUrl);

    int updatePassword(@Param("id")int id, @Param("password") String password);
}
