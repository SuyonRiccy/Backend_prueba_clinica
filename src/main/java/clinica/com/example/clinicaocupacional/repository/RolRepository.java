package clinica.com.example.clinicaocupacional.repository;

import clinica.com.example.clinicaocupacional.model.Rol;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends CrudRepository<Rol, Integer> {
	Optional<Rol> findByNombre(String nombre);
	List<Rol> findByVigente(boolean vigente);
}
