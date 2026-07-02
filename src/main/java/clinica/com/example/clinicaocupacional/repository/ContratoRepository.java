package clinica.com.example.clinicaocupacional.repository;

import clinica.com.example.clinicaocupacional.model.Contrato;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContratoRepository extends CrudRepository<Contrato, Integer> {
	List<Contrato> findByVigente(boolean vigente);
}
