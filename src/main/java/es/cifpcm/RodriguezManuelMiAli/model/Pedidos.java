package es.cifpcm.RodriguezManuelMiAli.model;

import jakarta.persistence.*;
import org.aspectj.weaver.ast.Var;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "pedidos")
public class Pedidos {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedido", nullable = false)
    private Integer id_pedido;

    @Column(name = "customer_id", nullable = false)
    private Integer customer_id;

    @Column(name = "productos", nullable = false)
    private String productos;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "precio_total", nullable = false)
    private Float precio_total;


    public int getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(int id_pedido) {
        this.id_pedido = id_pedido;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getProductos() {
        return productos;
    }

    public void setProductos(String productos) {
        this.productos = productos;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Float getPrecio_total() {
        return precio_total;
    }

    public void setPrecio_total(Float precio_total) {
        this.precio_total = precio_total;
    }
}
