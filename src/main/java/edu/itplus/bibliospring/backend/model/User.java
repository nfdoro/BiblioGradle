package edu.itplus.bibliospring.backend.model;

// a model nem szabad fugjon senkitol, ezert nincsenek benne metodusok, csak getter + setter van az adatagokhoz --POJO
// MODEL - egyedi azonosito kell ,
//       - kozos tulajdonsagokat ki emeljuk - hierarchikus struktura
public class User extends BaseEntity{
    private String userName;
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
