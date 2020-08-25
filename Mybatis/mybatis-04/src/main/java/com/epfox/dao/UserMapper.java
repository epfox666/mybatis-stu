package com.epfox.dao;

import com.epfox.pojo.User;

import java.util.List;
import java.util.Map;

public interface UserMapper {

    User selectUserById(int id);

    List<User> getUserByLimit(Map<String,Integer> map);

    List<User> getUserByRowBounds();
}
