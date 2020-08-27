package com.epfox.dao;

import com.epfox.pojo.Student;

import java.util.List;

public interface StudentMapper {

    //查询所有的学生信息，以及对应的老师的信息！
    public List<Student> getStudents();

    public List<Student> getStudents2();

}
