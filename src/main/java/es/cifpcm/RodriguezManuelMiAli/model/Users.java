package es.cifpcm.RodriguezManuelMiAli.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Integer user_id;
    @Column(name="user_name", nullable = false)
    private String user_name;
    @Column(name="password",nullable = false)
    private String password;

    public Users() {
    }
    /*
    public Users(Integer id, String user_name, String password) {
        this.user_id = id;
        this.user_name = user_name;
        this.password = password;
    }
     */

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}