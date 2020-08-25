package com.epfox.dao;

import com.epfox.pojo.User;

import java.util.List;

public interface UserMapper {

    User selectUserById(int id);
}
