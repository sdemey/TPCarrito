package es.cifpcm.RodriguezManuelMiAli.model;

import jakarta.persistence.*;

@Entity
@Table(name = "municipios")
public class Municipios {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_municipio", nullable = false)
    private Integer id_municipio;
    @Column(name = "id_provincia", nullable = false)
    private Integer id_provincia;
    @Column(name = "cod_municipio", nullable = false)
    private Integer cod_municipio;
    @Column(name = "DC", nullable = false)
    private Integer DC;
    @Column(name="nombre", nullable = false)
    private String nombre;

    public Municipios() {
    }
    /*
    public Municipios(Integer id_municipio, Integer id_provincia, Integer cod_municipio, Integer DC, String nombre) {
        this.id_municipio = id_municipio;
        this.id_provincia = id_provincia;
        this.cod_municipio = cod_municipio;
        this.DC = DC;
        this.nombre = nombre;
    }
     */

    public Integer getId_municipio() {
        return id_municipio;
    }

    public void setId_municipio(Integer id_municipio) {
        this.id_municipio = id_municipio;
    }

    public Integer getId_provincia() {
        return id_provincia;
    }

    public void setId_provincia(Integer id_provincia) {
        this.id_provincia = id_provincia;
    }

    public Integer getCod_municipio() {
        return cod_municipio;
    }

    public void setCod_municipio(Integer cod_municipio) {
        this.cod_municipio = cod_municipio;
    }

    public Integer getDC() {
        return DC;
    }

    public void setDC(Integer DC) {
        this.DC = DC;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}