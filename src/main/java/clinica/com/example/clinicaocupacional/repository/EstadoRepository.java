package clinica.com.example.clinicaocupacional.repository;

import clinica.com.example.clinicaocupacional.model.Estado;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoRepository extends CrudRepository<Estado, Integer> {
	List<Estado> findByVigente(boolean vigente);
}
