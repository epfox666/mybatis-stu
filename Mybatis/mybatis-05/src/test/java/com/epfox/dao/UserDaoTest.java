package com.epfox.dao;

import com.epfox.pojo.User;
import com.epfox.utils.MybatisUtils;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDaoTest {
    static Logger logger = Logger.getLogger(UserDaoTest.class);

    @Test
    public void getUserByRowBounds() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
//        mapper.addUser(new User(6,"老大","456456"));
//        mapper.updateUser(new User(6,"老二","456456999"));
        mapper.deleteUser(6);
        sqlSession.close();
    }

    //        List<User> userList = mapper.getUsers();
//    User userById = mapper.getUserById(1);
//        System.out.println(userById);
//        for (User user : userList) {
//            System.out.println(user);
//        }
}
