package com.community.dao;

import com.community.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

    User selectById(@Param("id") int id);

    User selectByName(String username);

    User selectByEmail(String email);

    int insertUser(User user);

    int updateStatus(@Param("id") int id, @Param("status") int status);

    int updateHeader(@Param("id") int id, @Param("headerUrl") String headerUrl);

    int updatePassword(int id, String password);
}
