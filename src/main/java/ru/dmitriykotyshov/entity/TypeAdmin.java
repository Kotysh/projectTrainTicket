package ru.dmitriykotyshov.entity;

import javax.persistence.*;

/**
 * Created by Дмитрий on 12.02.2018.
 */
@Entity
@Table(name = "TYPE_ADMIN")
@SequenceGenerator(name="entity_type_admin_seq", sequenceName = "TYPE_ADMIN_SEQ", initialValue = 1, allocationSize = 1)
public class TypeAdmin {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "entity_type_admin_seq")
    @Column(name = "TYPE_ADMIN_ID")
    private int id;

    @Column(name = "TYPE")
    private String type;

    public TypeAdmin(String type) {
        this.type = type;
    }

    public TypeAdmin() {
    }

    @Override
    public String toString() {
        return "TypeAdmin{" +
                "id=" + id +
                ", type='" + type + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}