package edu.itplus.bibliospring.backend.service.impl;

import edu.itplus.bibliospring.backend.model.User;
import edu.itplus.bibliospring.backend.repository.UserDAO;

import java.util.List;

public class TestUserDao implements UserDAO {
    public static User user;

    public TestUserDao(){
        this.user = new User();
        user.setPassword("123");
        user.setUuid("salt");
        user.setId(1L);
        user.setUsername("Pistike");
    }
    @Override
    public User findByID(Long id) {
       if(user.getId().equals(id)){
           return user;
       }
       else return null;
    }

    @Override
    public User create(User user) {
        return null;
    }

    @Override
    public User findByUsername(String username) {
        if(user.getUsername().equals(username)){
            return user;
        }
        return null;
    }

    @Override
    public void update(User user) {
        throw new UnsupportedOperationException("Not supported yet");
    }

    @Override
    public void delete(User user) {
        throw new UnsupportedOperationException("Not supported yet");
    }

    @Override
    public List<User> findAll() {
        return List.of(new User[]{user});
    }
}
