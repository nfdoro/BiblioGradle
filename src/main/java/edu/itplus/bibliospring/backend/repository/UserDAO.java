package edu.itplus.bibliospring.backend.repository;

import edu.itplus.bibliospring.backend.model.User;

import java.util.List;

// Adat hozza feresi komponsensek fuggetlenek kell legyenek
// egy dao, egy komponensert lesz felelos

public interface UserDAO extends BaseDAO<User,Long>{

    User findByUsername(String username);
}
