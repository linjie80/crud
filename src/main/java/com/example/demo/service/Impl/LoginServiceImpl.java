package com.example.demo.service.Impl;

import com.example.demo.dao.LoginDao;
import com.example.demo.entity.Login;
import com.example.demo.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 又见赤月君临 on 2020/1/2.
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    LoginDao loginDao;
    public Login getUser(String username, String password) {
        return loginDao.getByPasswordAndUsername(username,password);

    }

    public void insertUser(Login login) {
        loginDao.save(login);
    }
}
