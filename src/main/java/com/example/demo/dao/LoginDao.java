package com.example.demo.dao;

import com.example.demo.entity.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * Created by 又见赤月君临 on 2019/12/16.
 */
@Repository
public interface LoginDao extends JpaRepository<Login,Integer> {
    public Login getByPasswordAndUsername(String username,String password);
    //简单的增删改查不需要写，这个只不过为了辨别写了个名，不写也有底层的findById方法
    //LoginService findStudentById(Integer id);
    //稍复杂的SQL语句需要自定义，格式如下
    //@Query(name = "findStuByName",nativeQuery = true,value = "select * from login where name=:name")
    //List<LoginService> findStuByName(@Param("name") String name);

}
