package es.cifpcm.RodriguezManuelMiAli.dao;

import es.cifpcm.RodriguezManuelMiAli.model.Custormer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustormerRepository extends JpaRepository<Custormer, Integer> {
}