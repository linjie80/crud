package com.example.demo.service;

import com.example.demo.entity.Student;
import org.springframework.data.domain.Page;

import java.util.List;
/**
 * Created by 又见赤月君临 on 2019/12/16.
 */
public interface StudentService {

    Student save(Student student);
    void delete(Integer id);
    Student findStuById(Integer id);
    List<Student> findStuByName(String name);

    Page<Student> findAll(int page,int pageSize);

}
