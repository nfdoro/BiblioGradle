package edu.itplus.bibliospring.backend.repository.jpa;

import edu.itplus.bibliospring.backend.model.User;
import edu.itplus.bibliospring.backend.repository.UserDAO;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("JPA")
public class JpaUserDAO extends BaseDAOBean<User,Long> implements UserDAO {

    public JpaUserDAO() {
        super(User.class);
    }

    @Override
    public User findByUsername(String username) {
        return null;
    }
}
