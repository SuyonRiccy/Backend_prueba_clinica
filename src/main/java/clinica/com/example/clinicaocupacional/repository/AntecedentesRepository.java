package clinica.com.example.clinicaocupacional.repository;

import clinica.com.example.clinicaocupacional.model.Antecedentes;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AntecedentesRepository extends CrudRepository<Antecedentes, Integer> {
	List<Antecedentes> findByPaciente_Id(Integer pacienteId);
}
