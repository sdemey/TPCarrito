package es.cifpcm.RodriguezManuelMiAli.dao;

import es.cifpcm.RodriguezManuelMiAli.model.Pedidos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidosRepository extends JpaRepository<Pedidos, Integer> {
}