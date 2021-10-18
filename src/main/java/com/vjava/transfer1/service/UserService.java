package com.vjava.transfer1.service;

import com.vjava.transfer1.model.User;

import java.util.List;

public interface UserService {

    User findById(Long id);
    List<User> findAll();
    void saveUser(User user);
    void deleteById(Long id);
    User findByUsername(String username);
    void update(User user, Long id);

}
