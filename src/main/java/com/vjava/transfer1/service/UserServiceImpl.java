package com.vjava.transfer1.service;

import com.vjava.transfer1.dao.RoleDao;
import com.vjava.transfer1.dao.UserDao;
import com.vjava.transfer1.model.Role;
import com.vjava.transfer1.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @PersistenceContext
    private EntityManager em;
    private final RoleDao roleDao;
    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(RoleDao roleDao, UserDao userDao) {
        this.roleDao = roleDao;
        this.userDao = userDao;
    }


    @Override
    public User findById(Long id) {
        return userDao.getById(id);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(passwordEncoder().encode(user.getPassword()));
        Set<Role> roles = new HashSet<>();
        roles.add(roleDao.getById(1L));
        user.setRoles(roles);

        userDao.saveAndFlush(user);
    }

    @Override
    public void update(User user, Long id) {
        em.createQuery("UPDATE User u " +
                "SET u.username = :username, u.password = :password WHERE u.id = :id")
                .setParameter("username", user.getUsername())
                .setParameter("password", user.getPassword())
                .setParameter("id", id)
                .executeUpdate();
        em.getTransaction().commit();

    }

    @Override
    public void deleteById(Long id) {
        userDao.deleteById(id);
    }

    @Override
    public User findByUsername(String username) {
        em.getTransaction().begin();
        return (User) em.createQuery("SELECT u from User u where u.username = :username").setParameter("username", username).getSingleResult();
    }


    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
