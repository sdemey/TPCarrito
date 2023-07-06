package es.cifpcm.RodriguezManuelMiAli.model;

import jakarta.persistence.*;

@Entity
@Table(name = "provincias")
public class Provincias {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_provincia", nullable = false)
    private Integer id_provincia;
    @Column(name = "nombre", nullable = false)
    private String nombre;

    public Integer getId_provincia() {
        return id_provincia;
    }

    public void setId_provincia(Integer id_provincia) {
        this.id_provincia = id_provincia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}