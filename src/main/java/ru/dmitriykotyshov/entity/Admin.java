package ru.dmitriykotyshov.entity;

import javax.persistence.*;

/**
 * Created by Дмитрий on 12.02.2018.
 */
@Entity
@Table(name = "ADMIN")
@SequenceGenerator(name="entity_admin_seq", sequenceName = "ADMIN_SEQ", initialValue = 1, allocationSize = 1)
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "entity_admin_seq")
    @Column(name = "ADMIN_ID")
    private int id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PASS")
    private String pass;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TYPE_ADMIN_ID")
    private TypeAdmin typeAdmin;

    @Column(name = "BOSS")
    private Integer boss;

    public Admin(String name, String pass, TypeAdmin typeAdmin, Integer boss) {
        this.name = name;
        this.pass = pass;
        this.typeAdmin = typeAdmin;
        this.boss = boss;
    }

    public Admin() {

    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pass='" + pass + '\'' +
                ", typeAdmin=" + typeAdmin +
                ", boss=" + boss +
                '}';
    }

    public Integer getBoss() {
        return boss;
    }

    public void setBoss(Integer boss) {
        this.boss = boss;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public TypeAdmin getTypeAdmin() {
        return typeAdmin;
    }

    public void setTypeAdmin(TypeAdmin typeAdmin) {
        this.typeAdmin = typeAdmin;
    }
}
