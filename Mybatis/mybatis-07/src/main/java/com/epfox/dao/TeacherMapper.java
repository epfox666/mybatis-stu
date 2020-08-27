package com.epfox.dao;

import com.epfox.pojo.Teacher;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TeacherMapper {

    public Teacher getTeacher(int id);

    public Teacher getTeacher2(int id);

}
