package clinica.com.example.clinicaocupacional.repository;

import clinica.com.example.clinicaocupacional.model.HorarioMes;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HorarioMesRepository extends CrudRepository<HorarioMes, Integer> {
	List<HorarioMes> findByPersonal_Id(Integer personalId);
	List<HorarioMes> findByHorario_Id(Integer horarioId);
}
