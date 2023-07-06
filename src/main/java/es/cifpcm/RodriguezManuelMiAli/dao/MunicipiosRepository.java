package es.cifpcm.RodriguezManuelMiAli.dao;

import es.cifpcm.RodriguezManuelMiAli.model.Municipios;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

public interface MunicipiosRepository extends JpaRepository<Municipios, Integer> {
}