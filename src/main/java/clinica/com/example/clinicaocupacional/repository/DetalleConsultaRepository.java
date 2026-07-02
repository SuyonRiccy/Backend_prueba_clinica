package clinica.com.example.clinicaocupacional.repository;

import clinica.com.example.clinicaocupacional.model.DetalleConsulta;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleConsultaRepository extends CrudRepository<DetalleConsulta, Integer> {
	List<DetalleConsulta> findByConsulta_Id(Integer consultaId);
	List<DetalleConsulta> findByMedico_Id(Integer medicoId);
	List<DetalleConsulta> findByEstado_Id(Integer estadoId);
}
