package com.example.demo.service.Impl;

import com.example.demo.dao.StudentDao;
import com.example.demo.entity.Student;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 又见赤月君临 on 2019/12/16.
 */
@Service  //告诉springboot控制层实现类不然找不到注入
public class StudentServiceImpl implements StudentService {

    @Autowired //自动注入
    private StudentDao studentDao;

    @Override
    public Student save(Student student) {
        return studentDao.save(student);
    }

    @Override
    public void delete(Integer id) {
        studentDao.deleteById(id);
    }

    @Override
    public Student findStuById(Integer id) {
        return studentDao.findStudentById(id);
    }

    @Override
    public List<Student> findStuByName(String name) {
        return studentDao.findStuByName(name);
    }

    @Override
    public Page<Student> findAll(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page,pageSize);
        return studentDao.findAll(pageable);
    }
}
