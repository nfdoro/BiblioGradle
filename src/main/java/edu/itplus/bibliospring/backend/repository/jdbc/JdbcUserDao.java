package edu.itplus.bibliospring.backend.repository.jdbc;

import edu.itplus.bibliospring.backend.model.User;
import edu.itplus.bibliospring.backend.repository.RepositoryException;
import edu.itplus.bibliospring.backend.repository.UserDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Repository
@Profile("JDBC")
public class JdbcUserDao implements UserDAO {
    @Autowired
    private Logger LOG;
    @Autowired
    private ConnectionManager connectionManager;

    @Override
    public User findByID(Long id) {
        Connection connection = null;
        try{
            connection = connectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM  users WHERE id =?");
            preparedStatement.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setUuid(resultSet.getString("uuid"));
                return user;
            }
            return null;
        }catch (SQLException e){
            LOG.error("ERROR: findByID",e);
            throw new RepositoryException("ERROR : findByID",e);
        } finally {
            connectionManager.returnConection(connection);
        }
    }

    @Override
    public User create(User user) {
       Connection connection = null;
       try {
           connection = connectionManager.getConnection();
           PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users(username,password,uuid) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS);
           preparedStatement.setString(1,user.getUsername());
           preparedStatement.setString(2, user.getPassword());
           preparedStatement.setString(3, user.getUuid());
           preparedStatement.executeUpdate();
           ResultSet resultSet =  preparedStatement.getGeneratedKeys();
           resultSet.next();
           user.setId(resultSet.getLong(1));
           return  user;
       } catch (SQLException e){
           LOG.error("ERROR: create",e);
           throw new RepositoryException("ERROR : create",e);
       } finally {
           connectionManager.returnConection(connection);
       }
    }

    @Override
    public User findByUsername(String username) {
        Connection connection = null;
        try{
            connection = connectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE username=?");
            preparedStatement.setString(1,username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setUuid(resultSet.getString("uuid"));
                return user;
            }
            return null;
        }catch (SQLException e){
            LOG.error("ERROR: findByUsername",e);
            throw new RepositoryException("ERROR : findByUsername",e);
        } finally {
            connectionManager.returnConection(connection);
        }
    }

    @Override
    public void update(User user) {
        // hf
    }

    @Override
    public void delete(User user) {
        // hf
    }

    @Override
    public List<User> findAll() {
        Connection connection = null;
        List<User> users = new ArrayList<>();
        try {
            connection = connectionManager.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet= statement.executeQuery("SELECT * FROM users");
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setUuid(resultSet.getString("uuid"));
                users.add(user);
            }
            return users;
        }catch (SQLException e){
            LOG.error("ERROR: findAll",e);
            throw new RepositoryException("ERROR : findAll",e);
        }
        finally {
            connectionManager.returnConection(connection);
        }
    }
}
