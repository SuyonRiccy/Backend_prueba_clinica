package clinica.com.example.clinicaocupacional.repository;

import clinica.com.example.clinicaocupacional.model.Documento;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentoRepository extends CrudRepository<Documento, Integer> {
	List<Documento> findByVigente(boolean vigente);
}
