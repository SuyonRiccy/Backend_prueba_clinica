package clinica.com.example.clinicaocupacional.repository;

import clinica.com.example.clinicaocupacional.model.Empresa;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpresaRepository extends CrudRepository<Empresa, Integer> {
	List<Empresa> findBySector_Id(Integer sectorId);
	List<Empresa> findByVigente(boolean vigente);
	Optional<Empresa> findByNumeroDocumento(String numeroDocumento);
}
