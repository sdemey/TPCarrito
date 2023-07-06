package es.cifpcm.RodriguezManuelMiAli.model;

import java.time.LocalDate;
import java.util.List;

public class listadoPedido {
    private Custormer Cliente;
    private List<ProductoCantidad> ProductoCant;
    private LocalDate Fecha;
    private Float Total;

    public listadoPedido(Custormer cliente, List<ProductoCantidad> productoCant, LocalDate fecha, Float total) {
        Cliente = cliente;
        ProductoCant = productoCant;
        Fecha = fecha;
        Total = total;
    }

    public Custormer getCliente() {
        return Cliente;
    }

    public void setCliente(Custormer cliente) {
        Cliente = cliente;
    }

    public List<ProductoCantidad> getProductoCant() {
        return ProductoCant;
    }

    public void setProductoCant(List<ProductoCantidad> productoCant) {
        ProductoCant = productoCant;
    }

    public LocalDate getFecha() {
        return Fecha;
    }

    public void setFecha(LocalDate fecha) {
        Fecha = fecha;
    }

    public Float getTotal() {
        return Total;
    }

    public void setTotal(Float total) {
        Total = total;
    }
}
