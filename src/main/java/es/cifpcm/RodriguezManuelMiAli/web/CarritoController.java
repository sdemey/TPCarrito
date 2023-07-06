package es.cifpcm.RodriguezManuelMiAli.web;

import es.cifpcm.RodriguezManuelMiAli.dao.CustormerRepository;
import es.cifpcm.RodriguezManuelMiAli.dao.PedidosRepository;
import es.cifpcm.RodriguezManuelMiAli.dao.ProductofferRepository;
import es.cifpcm.RodriguezManuelMiAli.model.Custormer;
import es.cifpcm.RodriguezManuelMiAli.model.Pedidos;
import es.cifpcm.RodriguezManuelMiAli.model.ProductoCantidad;
import es.cifpcm.RodriguezManuelMiAli.model.Productoffer;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

@Controller
@SessionAttributes("carrito")
public class CarritoController {

    @Autowired
    ProductofferRepository productofferRepository;
    @Autowired
    PedidosRepository pedidosRepository;
    @Autowired
    CustormerRepository custormerRepository;
    private Float precioTotal = 0.0f;
    @GetMapping("/carrito")
    public String verCarrito(HttpSession session, Model model){
        List<ProductoCantidad> carrito = (List<ProductoCantidad>) session.getAttribute("carrito");
        if (carrito == null) {
            carrito = new ArrayList<>();
            session.setAttribute("carrito", carrito);
        }
        model.addAttribute("listaCarrito", carrito);
        model.addAttribute("total", precioTotal);
        return "carrito/carrito";
    }
    @RequestMapping("/annadirCarrito/{id}")
    public String annadirCarrito(@PathVariable("id") int id, Model model, HttpSession session){
        Productoffer producto =productofferRepository.findById(id).orElse(null);
        if( producto != null){
            Integer stockInicial = producto.getProduct_stock();
            if (stockInicial > 0) {

                List<ProductoCantidad> carrito = (List<ProductoCantidad>) session.getAttribute("carrito");
                if (carrito == null) {
                    carrito = new ArrayList<ProductoCantidad>();
                }
                ProductoCantidad proCant = carrito.stream().filter(prod ->
                        Objects.equals(prod.getProducto().getProduct_id(), producto.getProduct_id())).findFirst().orElse(null);
                if(proCant == null){
                    proCant = new ProductoCantidad(producto, 1);
                    carrito.add(proCant);
                    precioTotal = precioTotal + proCant.getProducto().getProduct_price();
                }else {
                    Integer cant = proCant.getCant();
                    if(cant >=stockInicial){
                        model.addAttribute("error", "No hay suficiente stock disponible para ese producto");
                        return "error/error";
                    }
                    proCant.setCant(proCant.getCant()+1);
                    precioTotal = precioTotal + proCant.getProducto().getProduct_price();
                }
                session.setAttribute("carrito", carrito);
                return "redirect:/carrito";
            } else {
                model.addAttribute("error", "No hay suficiente stock disponible para ese producto");
                return "error/error";
            }
        }
        else
        {
            model.addAttribute("error", "No existe ese producto");
            return "error/error";
        }

    }
    @RequestMapping("/quitarCarrito/{id}")
    public String quitarCarrito (@PathVariable("id") int id, Model model, HttpSession session){
        Productoffer producto =productofferRepository.findById(id).orElse(null);
        if(producto != null){
            List<ProductoCantidad> carrito = (List<ProductoCantidad>) session.getAttribute("carrito");
            if (carrito != null) {
                //carrito.remove(producto); //NO ME FUNCIONA
                precioTotal = precioTotal - producto.getProduct_price();
                carrito.removeIf(p -> p.getProducto().getProduct_id() == id);
                session.setAttribute("carrito", carrito);
            }
            return "redirect:/carrito";
        }
        else
        {
            model.addAttribute("error", "No existe ese producto");
            return "error/error";
        }
    }
    @GetMapping("/comprarCarrito")
    public String mostrarComprar(Model model, HttpSession session ){
        List < Custormer> clientes = custormerRepository.findAll();
        model.addAttribute("listaClientes", clientes);
        return "carrito/comprar";
    }
    @PostMapping("/comprarCarrito")
    public String comprarCarrito (@RequestParam("selectCliente") Integer idCustomer, HttpSession session){
        Pedidos pedidoNuevo = new Pedidos();
        List<Pedidos> listaPedidos =  pedidosRepository.findAll();
        int maxId = (listaPedidos.stream().mapToInt(Pedidos::getId_pedido)
                .max().orElse(0))+1;
        pedidoNuevo.setId_pedido(maxId);
        pedidoNuevo.setCustomer_id(idCustomer);
        StringBuilder carritoString = new StringBuilder();
        List<ProductoCantidad> carrito = (List<ProductoCantidad>) session.getAttribute("carrito");
        for (ProductoCantidad productoCantidad : carrito) {
            Productoffer producto = productofferRepository.findById(productoCantidad.getProducto().getProduct_id()).orElse(null);
            Integer stockIncial = producto.getProduct_stock();
            producto.setProduct_stock(stockIncial - productoCantidad.getCant());
            productofferRepository.save(producto);
            carritoString.append("(" + productoCantidad.getProducto().getProduct_id() + "," + productoCantidad.getCant() + ");");
        }
        String carritoFinal = carritoString.toString();
        pedidoNuevo.setProductos(carritoFinal);
        pedidoNuevo.setFecha( LocalDate.now());
        pedidoNuevo.setPrecio_total(precioTotal);
        pedidosRepository.save(pedidoNuevo);
        precioTotal = 0.0f;
        carrito.clear();
        return "redirect:/pedidos";
    }

}
