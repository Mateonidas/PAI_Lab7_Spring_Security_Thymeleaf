package com.mpolec.lab7.dao;

import com.mpolec.lab7.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface userDao extends JpaRepository<User, Integer> {
    public User findByLogin(String login);
}
