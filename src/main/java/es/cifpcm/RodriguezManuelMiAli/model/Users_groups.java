package es.cifpcm.RodriguezManuelMiAli.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users_groups")
public class Users_groups {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "group_id ", nullable = false)
    private Integer group_id ;
    @Column(name = "user_name  ", nullable = false)
    private String user_name;

    public Users_groups() {
    }
    public Integer getGroup_id() {
        return group_id;
    }

    public void setGroup_id(Integer group_id) {
        this.group_id = group_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
}