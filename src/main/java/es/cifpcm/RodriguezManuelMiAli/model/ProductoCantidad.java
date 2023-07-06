package es.cifpcm.RodriguezManuelMiAli.model;

public class ProductoCantidad {
    private Productoffer producto;
    private Integer cant;

    public ProductoCantidad(Productoffer producto, Integer cant) {
        this.producto = producto;
        this.cant = cant;
    }

    public Productoffer getProducto() {
        return producto;
    }

    public void setProducto(Productoffer producto) {
        this.producto = producto;
    }

    public Integer getCant() {
        return cant;
    }

    public void setCant(Integer cant) {
        this.cant = cant;
    }
}
