package com.example.demo.controller;

import com.example.demo.entity.Student;
import com.example.demo.service.StudentService;
//import net.sf.jsqlparser.expression.JsonExpression;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by 又见赤月君临 on 2019/12/16.
 */
@RestController  //控制层注解并实现返回格式为json
@RequestMapping("/s") //配置访问路径
public class StudentController {

    private StudentService studentService;
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
    /* @PostMapping("/test")//测试控制器
    public String test(){
         JSONObject json = new JSONObject();
         json.put("name","小明");
         json.put("age","20");
         json.put("sex","男");
         return json.toString();
     }*/
    @PostMapping("/add") //post请求
    public Student save(Student student) {
        //JSONObject user = JSONObject.parseObject(student);
        System.out.println("JsonToObject-->"+student);
        return studentService.save(student);
    }

    @PostMapping("/update")
    public Student update(Student student) {
        Student s = studentService.findStuById(student.getId());
        s.setName(student.getName());
        s.setAge(student.getAge());
        s.setSex(student.getSex());
        return studentService.save(s);
    }

    @GetMapping("/del/{id}") //get请求 RESTful风格
    public String del(@PathVariable int id) {
        studentService.delete(id);
        return "yes";
    }

    @GetMapping("findByName/{name}")
    public List<Student> findByName(@PathVariable String name) {
        return studentService.findStuByName(name);
    }

    @GetMapping("/query")
    public Page<Student> findByPage(Integer page, HttpServletResponse response) {

        response.setHeader("Access-Control-Allow-Origin","*");//解决跨域请求

        if (page == null || page <= 0) {
            page = 0;
        } else {
            page -= 1;
        }
        return studentService.findAll(page, 5);
    }
}
