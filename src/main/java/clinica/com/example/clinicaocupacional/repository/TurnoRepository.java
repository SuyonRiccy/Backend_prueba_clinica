package clinica.com.example.clinicaocupacional.repository;

import clinica.com.example.clinicaocupacional.model.Turno;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TurnoRepository extends CrudRepository<Turno, Integer> {
	List<Turno> findByVigente(boolean vigente);
}
