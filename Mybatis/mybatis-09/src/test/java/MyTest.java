import com.epfox.dao.UserMapper;
import com.epfox.pojo.User;
import com.epfox.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class MyTest {
    @Test
    public void queryUsersById(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user1 = mapper.queryUsersById(1);
        System.out.println(user1);

        System.out.println("==============================");

        User user2 = mapper.queryUsersById(1);
        System.out.println(user2);

        System.out.println(user1==user2);
        sqlSession.close();
    }
    @Test
    public void queryUsersById2(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user1 = mapper.queryUsersById(1);
        System.out.println(user1);
        mapper.updateUser(new User(2,"epfox666","666"));
        System.out.println("==============================");
        User user2 = mapper.queryUsersById(1);
        System.out.println(user2);

        System.out.println(user1==user2);
        sqlSession.close();
    }
    @Test
    public void queryUsersById3(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user1 = mapper.queryUsersById(1);
        System.out.println(user1);
//        mapper.updateUser(new User(2,"epfox666","666"));
        sqlSession.clearCache();
        System.out.println("==============================");
        User user2 = mapper.queryUsersById(1);
        System.out.println(user2);

        System.out.println(user1==user2);
        sqlSession.close();
    }

    @Test
    public void queryUsersById4(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        SqlSession sqlSession2 = MybatisUtils.getSqlSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user1 = mapper.queryUsersById(1);
        System.out.println(user1);
        sqlSession.close();


        UserMapper mapper2 = sqlSession2.getMapper(UserMapper.class);
        User user2 = mapper2.queryUsersById(1);
        System.out.println(user2);

        System.out.println(user1==user2);
        sqlSession2.close();
    }

    @Test
    public void queryUsersById5(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        SqlSession sqlSession2 = MybatisUtils.getSqlSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user1 = mapper.queryUsersById(1);
        System.out.println(user1);
        sqlSession.close();

        UserMapper mapper2 = sqlSession2.getMapper(UserMapper.class);
        User user2 = mapper2.queryUsersById(1);
        System.out.println(user2);
        User user3 = mapper2.queryUsersById(2);
        System.out.println(user3);
        User user4 = mapper2.queryUsersById(2);
        System.out.println(user4);
        sqlSession2.close();
    }
}
