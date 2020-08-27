import com.epfox.dao.StudentMapper;
import com.epfox.dao.TeacherMapper;
import com.epfox.pojo.Student;
import com.epfox.pojo.Teacher;
import com.epfox.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class MyTest {
    public static void main(String[] args) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        TeacherMapper mapper = sqlSession.getMapper(TeacherMapper.class);
        Teacher teacher = mapper.getTeacher(1);
        System.out.println(teacher);
        sqlSession.close();
    }

    @Test
    public void testStudents(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        List<Student> studentList = mapper.getStudents();
        for (Student student : studentList) {
            System.out.println(student);
        sqlSession.close();
    }
}

    @Test
    public void testStudents2(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        List<Student> studentList = mapper.getStudents2();
        for (Student student : studentList) {
            System.out.println(student);
        }
        sqlSession.close();
    }
}

