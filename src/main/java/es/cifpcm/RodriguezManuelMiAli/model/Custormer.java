package es.cifpcm.RodriguezManuelMiAli.model;

import jakarta.persistence.*;

@Entity
@Table(name = "customer")
public class Custormer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id", nullable = false)
    private Integer customer_id;
    @Column(name = "first_name", nullable = false)
    public String first_name;
    @Column(name = "last_name", nullable = false)
    private String last_name;
    @Column(name = "telefono", nullable = false)
    private String telefono;
    @Column(name = "email")
    private String email;

    public Custormer() {
    }

    public Integer getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Integer customer_id) {
        this.customer_id = customer_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}