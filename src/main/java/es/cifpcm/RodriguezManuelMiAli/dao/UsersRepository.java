package es.cifpcm.RodriguezManuelMiAli.dao;
import es.cifpcm.RodriguezManuelMiAli.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
//todos los metodos que necesitamos para conectar con la base de datos y traer info , findAll()
public interface UsersRepository extends JpaRepository<Users, Integer> {
}