package clinica.com.example.clinicaocupacional.repository;

import clinica.com.example.clinicaocupacional.model.Sector;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SectorRepository extends CrudRepository<Sector, Integer> {
	List<Sector> findByVigente(boolean vigente);
}
