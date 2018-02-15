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

    @Column(name = "TYPE_ADMIN")
    private String TypeAdmin;

    public TypeAdmin(String typeAdmin) {
        TypeAdmin = typeAdmin;
    }

    public TypeAdmin() {
    }

    @Override
    public String toString() {
        return "TypeAdmin{" +
                "id=" + id +
                ", TypeAdmin='" + TypeAdmin + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypeAdmin() {
        return TypeAdmin;
    }

    public void setTypeAdmin(String typeAdmin) {
        TypeAdmin = typeAdmin;
    }
}