package es.cifpcm.RodriguezManuelMiAli.model;

import jakarta.persistence.*;

@Entity
@Table(name = "groups")
public class Groups {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "group_id", nullable = false)
    private Integer group_id;
    @Column(name = "group_name", nullable = false)
    private String group_name;
    @Column(name = "description")
    private String description;

    public Groups() {
    }
   /*
    public Groups(Integer id, String group_name, String description) {
        this.group_id = id;
        this.group_name = group_name;
        this.description = description;
    }
   */

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}