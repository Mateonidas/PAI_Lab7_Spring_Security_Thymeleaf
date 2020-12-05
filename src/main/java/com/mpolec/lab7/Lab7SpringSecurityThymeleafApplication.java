package com.mpolec.lab7;

import com.mpolec.lab7.dao.userDao;
import com.mpolec.lab7.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class Lab7SpringSecurityThymeleafApplication {

    @Autowired
    private userDao dao;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public static void main(String[] args) {
        SpringApplication.run(Lab7SpringSecurityThymeleafApplication.class, args);
    }

//    @PostConstruct
//    public void init() {
//        dao.save(
//                new User(
//                        "admin",
//                        "admin",
//                        "admin",
//                        passwordEncoder.encode("passwd")));
//    }
}
