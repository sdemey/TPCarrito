package es.cifpcm.RodriguezManuelMiAli.model;

import jakarta.persistence.*;

@Entity
@Table(name = "membership")
public class Membership {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_name", nullable = false)
    private Integer user_name;
    @Column(name = "group_id", nullable = false)
    private Integer group_id;
    @Column(name = "group_name", nullable = false)
    private String  group_name;

    public Membership() {
    }
    /*
    public Membership(Integer user_name, Integer group_id, String group_name) {
        this.user_name = user_name;
        this.group_id = group_id;
        this.group_name = group_name;
    }
     */

    public Integer getUser_name() {
        return user_name;
    }

    public void setUser_name(Integer user_name) {
        this.user_name = user_name;
    }

    public Integer getGroup_id() {
        return group_id;
    }

    public void setGroup_id(Integer group_id) {
        this.group_id = group_id;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }
}