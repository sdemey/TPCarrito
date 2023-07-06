package es.cifpcm.RodriguezManuelMiAli.web;

import es.cifpcm.RodriguezManuelMiAli.dao.CustormerRepository;
import es.cifpcm.RodriguezManuelMiAli.dao.PedidosRepository;
import es.cifpcm.RodriguezManuelMiAli.dao.ProductofferRepository;
import es.cifpcm.RodriguezManuelMiAli.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
@Controller
public class PedidoController {
    @Autowired
    ProductofferRepository productofferRepository;
    @Autowired
    PedidosRepository pedidosRepository;
    @Autowired
    private CustormerRepository custormerRepository;

    @GetMapping("/pedidos")
    public String ListaPedidos (Model model){
        List<Pedidos> pedidos = pedidosRepository.findAll();
        List<listadoPedido> listado = obtenerPedido(pedidos);
        model.addAttribute("lista", listado);
        return "products/listaPedidos";
    }

    public List<listadoPedido> obtenerPedido (List<Pedidos> pedido){
        List<listadoPedido> listado = new ArrayList<>();
        pedido.forEach( pedidos -> {
            Custormer cliente = custormerRepository.findById(pedidos.getCustomer_id()).orElse(null);
            String[]  dividir = pedidos.getProductos().split(";");
            ArrayList <ProductoCantidad> proCant =new ArrayList<>();
            for (int i =0; i < dividir.length; i++){
                int inicio = dividir[i].indexOf("(");
                int fin = dividir[i].indexOf(")");
                String numeros =  dividir[i].substring(inicio + 1, fin);
                String[]arrNumeros = numeros.split(",");
                Productoffer pro = productofferRepository.findById(Integer.parseInt(arrNumeros[0])).orElse(null);
                if(pro != null){
                    proCant.add(new ProductoCantidad(pro, Integer.parseInt(arrNumeros[1])));
                }
            }
            listado.add( new listadoPedido(cliente, proCant, pedidos.getFecha(), pedidos.getPrecio_total()));
        });
        return listado;
    }

}
