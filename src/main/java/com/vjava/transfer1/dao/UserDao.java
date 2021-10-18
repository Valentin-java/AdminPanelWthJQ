package com.vjava.transfer1.dao;

import com.vjava.transfer1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long> {

}
