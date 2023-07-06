package es.cifpcm.RodriguezManuelMiAli.web;
import es.cifpcm.RodriguezManuelMiAli.dao.*;
import es.cifpcm.RodriguezManuelMiAli.model.*;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.CustomSQLErrorCodesTranslation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
//import java.awt.print.Pageable;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class ProductsController {
    @Autowired
    ProductofferRepository productofferRepository;
    @Autowired
    MunicipiosRepository municipiosRepository;
    @Autowired
    ProvinciasRepository provinciasRepository;
    @Autowired
    PedidosRepository pedidosRepository;
    @Autowired
    private CustormerRepository custormerRepository;
    @GetMapping("/products")
    public String productos (Model model){
        model.addAttribute("products", productofferRepository.findAll());
        List<Provincias> provi = provinciasRepository.findAll();
        model.addAttribute("listaProvincia", provinciasRepository.findAll());
        return "products/products";
    }
    @GetMapping("/productsSelect")
    public String productsSelect (@RequestParam("selectMuni") int idMunicipio, Model model){
        if(idMunicipio != 0){
            List<Productoffer> listaproductos = productofferRepository.findAll()
                    .stream().filter(pro -> pro.getId_municipio() == idMunicipio ).toList();
            Municipios muni = municipiosRepository.findById(idMunicipio).orElse(null);
            if(muni != null){
                if((long) listaproductos.size() !=0 ){

                    model.addAttribute("nombreMunicipio", muni.getNombre());
                    model.addAttribute("products", listaproductos);
                    model.addAttribute("listaProvincia", provinciasRepository.findAll());
                }
                else{
                    model.addAttribute("nombreMunicipio", muni.getNombre());
                    model.addAttribute("listaProvincia", provinciasRepository.findAll());
                    model.addAttribute("mensaje", "No hay productos en este municipio !!!!!");
                }
            }
        }
        else
        {
            model.addAttribute("mensaje", "No se ha seleccionado un municipio");
        }

        return "products/products";
    }
    @GetMapping("/productsCreate")
    public String createProducts(Model model){
        model.addAttribute("producto", new Productoffer());
        model.addAttribute("listaProvincia", provinciasRepository.findAll());
        return "products/createProducts";
    }
    @PostMapping("/productsCreate")
    public String createProducts(@ModelAttribute Productoffer producto,@RequestParam("file") File file , Model model) {
        if (!Float.class.isInstance(producto.getProduct_price())) { //ya lo compruebo con el required lo de nulo
            model.addAttribute("error", "el precio debe ser un numero");
            return "error/error";
        }
        List<Productoffer> listaproductos = productofferRepository.findAll();
        int maxId = (listaproductos.stream().mapToInt(Productoffer::getProduct_id)
                .max().orElse(0))+1;
        producto.setProduct_picture(file.getName());
        /*
        try {
            URL url = getClass().getClassLoader().getResource("static/imagenes/");
            if(url != null){
               // Path path = Path.of(url + file.getName());
                File archivoNuevo =  new File(String.valueOf(url.toURI()), file.getName());
                System.out.println("Sí se ha cargado");
            }
        }catch (Exception e){
            System.out.println(e);
        }
         */
        producto.setProduct_id(maxId);
        if(producto.getProduct_stock() >=0 ){
            productofferRepository.save(producto);
            return "redirect:/products";
        }
        else {
            model.addAttribute("error", "No se permite stocks negativos");
            return "error/error";
        }
    }
    @RequestMapping("/productsDelete/{id}")
     public String borrar (@PathVariable("id") int id, Model model){
        Productoffer producto =productofferRepository.findById(id).orElse(null);
        if(producto != null){
           productofferRepository.delete(producto);
        }
        else
        {
            model.addAttribute("error", "No existe ese producto");
            return "redirect:/error/error";
        }
        return "redirect:/products";
    }
    @GetMapping("/productsEdit/{id}")
    public String editarMostrar (@PathVariable("id") int id, Model model){
        Productoffer producto =productofferRepository.findById(id).orElse(null);
        if(producto != null){
            model.addAttribute("producto", producto);
            model.addAttribute("listaProvincia", provinciasRepository.findAll());
        }
        return  "products/editProducts";
    }
    @PostMapping("/producstActualizar/{id}")
    public String editarProducto (@PathVariable("id") int id, @ModelAttribute Productoffer productoRecibido, Model model){
        Productoffer producto =productofferRepository.findById(id).orElse(null);
        if(producto != null){
            producto.setProduct_name(productoRecibido.getProduct_name());
            producto.setProduct_price(productoRecibido.getProduct_price());
            producto.setId_municipio(productoRecibido.getId_municipio());
            if(productoRecibido.getProduct_stock() >=0 ){
                producto.setProduct_stock(productoRecibido.getProduct_stock());
                productofferRepository.save(producto);
            }
            else {
                model.addAttribute("error", "No se permite stocks negativos");
                return "error/error";
            }
        }
        else
        {
            model.addAttribute("error", "No existe ese producto");
            return "redirect:/error/error";
        }
        return "redirect:/products";
    }

}
