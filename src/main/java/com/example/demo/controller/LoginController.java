package com.example.demo.controller;

import com.example.demo.entity.Login;
import com.example.demo.service.Impl.LoginServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by 又见赤月君临 on 2020/1/2.
 */

@Controller
@RequestMapping("/") //配置访问路径
public class LoginController {
    @Autowired
    LoginServiceImpl loginServiceImpl;
    @RequestMapping("/index")
    public String index(ModelMap map) {
        // 加入一个属性，用来在模板中读取
        map.addAttribute("host", "http://www.w3cjava.com");
        // return模板文件的名称，对应src/main/resources/templates/index.html
        return "index/index";
    }
    @RequestMapping("/login")
    public String login(Login login, Map<String,Object> map,HttpServletRequest request){
        if(login.getPassword()!=null&&login.getUsername()!=null) {
            if(login.getPassword()!=""&&login.getUsername()!="") {
                Login user1 = loginServiceImpl.getUser(login.getPassword(), login.getUsername());
                if (user1 == null) {
                    map.put("msg", "用户名密码错误");
                    return "login/login";
                } else {
                    HttpSession sessoin = request.getSession();//这就是session的创建
                    sessoin.setAttribute("userLogin", user1);
                    sessoin.setMaxInactiveInterval(20 * 60);
                    map.put("msg", "登入成功");
                    return "redirect:/index";
                }
            }
            else {
                map.put("msg", "用户名密码不能为空");
                return "login/login";
            }
        }
        else {return "login/login";}
    }

    @RequestMapping("/regist")
    public String regist(Login login, Map<String,Object> map){
        if(login.getPassword()!=null&&login.getUsername()!=null) {
            if (login.getPassword()!=""&&login.getUsername()!="") {
                loginServiceImpl.insertUser(login);
                map.put("msg", "注册成功");
                return "login/login";
            } else {
                map.put("msg", "用户信息不能为空");
                return "login/regist";
            }
        }
        else {return "login/regist";}
    }

}
