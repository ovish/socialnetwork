package com.solvd.socialnetwork.mybatis;

import com.solvd.socialnetwork.model.User;

import java.util.List;

public interface UserMapper {

    void insert(User user);
    User findById(Long id);
    List<User> findAll();
    void update(User user);
    void deleteById(Long id);
}
