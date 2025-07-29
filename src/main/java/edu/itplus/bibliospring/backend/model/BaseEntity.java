package edu.itplus.bibliospring.backend.model;

import jakarta.persistence.*;

@MappedSuperclass
public class BaseEntity extends AbstractModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //autoincrement
    @Column(name = "id")
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
