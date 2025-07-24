package edu.itplus.bibliospring.backend.repository.memory;

import edu.itplus.bibliospring.backend.model.User;
import edu.itplus.bibliospring.backend.repository.UserDAO;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
@Repository
@Profile("Memory")
public class MemoryUserDAO implements UserDAO {

    private ConcurrentHashMap<Long,User> users;
    private AtomicLong idGenerator; // a random id-hoz

    public MemoryUserDAO(){
        users = new ConcurrentHashMap<>();
        idGenerator = new AtomicLong();
    }
    @Override
    public User findByID(Long id) {
        return users.get(id);
    }

    @Override
    public User create(User user) {
        user.setId(idGenerator.incrementAndGet());
        user.getUuid();
        users.put(user.getId(),user);
        return user;
    }

    @Override
    public User findByUsername(String username) {
        return users.values().stream().filter(user -> user.getUsername().equals(username)).findAny().orElse(null);
    }

    @Override
    public void update(User user) {
        users.put(user.getId(),user);
    }

    @Override
    public void delete(User user) {
        users.remove(user.getId(),user);
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<User>(users.values());
    }
}
