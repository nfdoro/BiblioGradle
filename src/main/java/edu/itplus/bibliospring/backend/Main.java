package edu.itplus.bibliospring.backend;

import edu.itplus.bibliospring.backend.model.User;
import edu.itplus.bibliospring.backend.service.LoginService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {

    @Autowired
    private LoginService loginService;

    public static void main(String[] args) {

        SpringApplication.run(Main.class,args);
    }

    @PostConstruct
    public void postConstruct(){
        User testUser = new User();
        testUser.setUsername("Lajoska22");
        testUser.setPassword("cica12334");

        //loginService.register(testUser);
        //testUser.setPassword("cica12334");

        System.out.println(loginService.login(testUser));
        System.out.println(testUser);
    }
}