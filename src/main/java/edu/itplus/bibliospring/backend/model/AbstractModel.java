package edu.itplus.bibliospring.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;

import java.util.Objects;
import java.util.UUID;
@MappedSuperclass
public class AbstractModel {

    @Column(name = "uuid", nullable = false,unique = true, length = 36)
    private String uuid;

    public String getUuid() {
        if (uuid == null) {
            uuid = UUID.randomUUID().toString();
        }
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        AbstractModel that = (AbstractModel) o;
        return Objects.equals(getUuid(), that.getUuid());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getUuid());
    }

    // a lazy generalas miatt kell hogy biztosan letre hozza
    @PrePersist
    public void onPrePersist(){
        getUuid();
    }
}
