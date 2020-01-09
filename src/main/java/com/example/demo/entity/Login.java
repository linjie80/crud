package com.example.demo.entity;

import javax.persistence.*;
/**
 * Created by 又见赤月君临 on 2019/12/30.
 */
@Entity //告诉springboot这是实体类，有需要，来这找
@Table(name = "login") //对应数据库中的表，配合application.properties可以自动生成student表
public class Login {
    @Id //设置主键
    @GeneratedValue(strategy = GenerationType.IDENTITY) //自增
    private Integer id;
    private String name;
    private String username;
    private String password;

    public Login() {
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", username=" + username +
                ", password='" + password + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
