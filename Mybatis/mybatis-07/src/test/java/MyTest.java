import com.epfox.dao.StudentMapper;
import com.epfox.dao.TeacherMapper;
import com.epfox.pojo.Student;
import com.epfox.pojo.Teacher;
import com.epfox.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class MyTest {

    @Test
    public void test(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        TeacherMapper mapper = sqlSession.getMapper(TeacherMapper.class);
        List<Teacher> TeacherList = mapper.getTeacher();
        for (Teacher teacher : TeacherList) {
            System.out.println(teacher);
        }
        sqlSession.close();
    }

}

