package com.epfox.dao;

import com.epfox.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    List<User> getUserList();

    User selectUserById(int id);

    User selectUserByNP(@Param("username") String username,@Param("pwd") String pwd);

    User selectUserByNP2(Map<String, Object> map);

    int addUser(User user);

    int addUser2(Map<String, Object> map);

    int updateUser(User user);

    int deleteUser(int id);

    List<User> selectlike(String name);

    List<User> selectlike2(String name);
}
