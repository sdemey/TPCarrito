package es.cifpcm.RodriguezManuelMiAli.web;
import es.cifpcm.RodriguezManuelMiAli.dao.UsersRepository;
import es.cifpcm.RodriguezManuelMiAli.model.Productoffer;
import es.cifpcm.RodriguezManuelMiAli.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable; //si no me funciona bien los requestpage
//import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UsersController {
    @Autowired
    UsersRepository usersRepository;

    @GetMapping("/users")
    public String users (Model model) {
        /*
        Pageable sortedByIDDesc =
                (Pageable) PageRequest.of(0, 10, Sort.by("id"));
                 model.addAttribute("users", usersRepository.findAll((PageRequest) sortedByIDDesc));
         */
        model.addAttribute("users", usersRepository.findAll());
        return "users/users";
    }
 /*
    @GetMapping("/usersCreate")
    public String showUsersCreate ( Model model){
        model.addAttribute("usuario", new Users());
        return "users/createUser";
    }

    @GetMapping("/usersEdit/{id}")
    public String editarMostrar (@PathVariable("id") int id, Model model){
        Users usuario =usersRepository.findById(id).orElse(null);
        if(usuario != null){
            model.addAttribute("usuario", usuario);
        }
        return  "products/editProducts";
    }

    @PostMapping("/usuarioActualizar/{id}")
    public String editarUsuario (@PathVariable("id") int id, @ModelAttribute Users usuarioRecibido, Model model){
        Users usuario =usersRepository.findById(id).orElse(null);
        if( usuario != null){
            usuario.setUser_name(usuarioRecibido.getUser_name());
            usuario.setPassword(usuarioRecibido.getPassword());
        }
        else
        {
            model.addAttribute("error", "No existe ese producto");
            return "error/error";
        }
        return "redirect:/users";
    }
    /*
    @PostMapping("/usersCreate")
    public String UsersCreate(@ModelAttribute Users user) {
        List<Users> listaUsers = usersRepository.findAll();
        int maxId = (listaUsers.stream().mapToInt(Users::getId)
                .max().orElse(0))+1;
        user.setId(maxId+1);
        usersRepository.save(user);
        return "redirect:/users";
    }
     */
}
