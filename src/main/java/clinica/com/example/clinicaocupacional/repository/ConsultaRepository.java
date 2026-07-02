package clinica.com.example.clinicaocupacional.repository;

import clinica.com.example.clinicaocupacional.model.Consulta;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultaRepository extends CrudRepository<Consulta, Integer> {
	List<Consulta> findByPaciente_Id(Integer pacienteId);
	List<Consulta> findByEstado_Id(Integer estadoId);

	List<Consulta> findByFechaConsulta(LocalDate fechaConsulta);
}
