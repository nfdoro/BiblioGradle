package edu.itplus.bibliospring.backend.repository.jdbc;


import edu.itplus.bibliospring.backend.repository.RepositoryException;
import edu.itplus.bibliospring.backend.utils.PropertyProvider;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;

// singleton kell legyen
@Component
public class ConnectionManager {
    public final int POOL_SIZE = 10;
    private LinkedList<Connection> pool;
    @Autowired
    private Logger LOG;

    @Autowired
    private PropertyProvider propertyProvider;

    @PostConstruct
    public void postConstruct(){
        pool = new LinkedList<>();
        try {
            Class.forName(propertyProvider.getProperty("JDBC_DRIVER"));
            for (int i = 0; i < Integer.parseInt(propertyProvider.getProperty("POOL_SIZE")); i++) {
                pool.add(DriverManager.getConnection(propertyProvider.getProperty("JDBC_URL"),propertyProvider.getProperty("JDBC_USER"),
                        propertyProvider.getProperty("JDBC_PASSWORD")));
            }
            LOG.info("Connection pool inicialized succesfuly");
        } catch (ClassNotFoundException | SQLException e) {
            LOG.error("ERROR: Connection pooling failure",e);
            throw new RepositoryException("ERROR : Connection pooling failure",e);
        }
    }

    public Connection getConnection(){
        if (!pool.isEmpty()){
            return  pool.pop();
        }
        return null;
    }
    public void returnConection(Connection connection){
        if (pool.size() < Integer.parseInt(propertyProvider.getProperty("POOL_SIZE"))){
            pool.push(connection);
        }
    }
}
