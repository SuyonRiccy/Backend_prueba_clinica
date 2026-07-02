package clinica.com.example.clinicaocupacional.repository;

import clinica.com.example.clinicaocupacional.model.Especialidad;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EspecialidadRepository extends CrudRepository<Especialidad, Integer> {
	List<Especialidad> findByVigente(boolean vigente);
}
