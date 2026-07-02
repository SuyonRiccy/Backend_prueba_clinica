package clinica.com.example.clinicaocupacional.repository;

import clinica.com.example.clinicaocupacional.model.Horario;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HorarioRepository extends CrudRepository<Horario, Integer> {
	List<Horario> findByContrato_Id(Integer contratoId);
	List<Horario> findByTurno_Id(Integer turnoId);
}
