package edu.itplus.bibliospring.backend.repository;

import edu.itplus.bibliospring.backend.model.User;

import java.util.List;

// Adat hozza feresi komponsensek fuggetlenek kell legyenek
// egy dao, egy komponsnesert lesz felelos

public interface UserDAO {

    User findByID (Long id);
    User create(User user);
    User findByUsername(String username);
    void update(User user);
    void delete(User user);
    List<User> findAll();
}
