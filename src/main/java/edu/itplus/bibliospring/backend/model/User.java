package edu.itplus.bibliospring.backend.model;

// a model nem szabad fugjon senkitol, ezert nincsenek benne metodusok, csak getter + setter van az adatagokhoz --POJO
// MODEL - egyedi azonosito kell ,
//       - kozos tulajdonsagokat ki emeljuk - hierarchikus struktura

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User extends BaseEntity{

    @Column(name = "username",nullable = false,unique = true,length = 45)
    private String userName;

    @Column (name = "password",nullable = false,length = 64)
    private String password;

    public String getUsername() {
        return userName;
    }

    public void setUsername(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString(){
        return "Hi!: "+ getUuid() + " " + getId() +  " - " + getUsername() +  " passwd:" + getPassword();
    }
}
