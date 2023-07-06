package es.cifpcm.RodriguezManuelMiAli.web;

import es.cifpcm.RodriguezManuelMiAli.dao.MunicipiosRepository;
import es.cifpcm.RodriguezManuelMiAli.model.Municipios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class MunicipiosController {
    @Autowired
    private MunicipiosRepository municipiosRepository;
    @RequestMapping(value = "/encontrarMunicipios", method = RequestMethod.GET, produces =  MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<Municipios>> MostrarMunicipios(@RequestParam int provinciaId) {
        List<Municipios> municipios = municipiosRepository.findAll()
                .stream().filter(muni-> muni.getId_provincia() ==  provinciaId ).collect(Collectors.toList());
        return new ResponseEntity<List<Municipios>>(municipios, HttpStatus.OK);
    }
}
