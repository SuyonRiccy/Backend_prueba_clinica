package clinica.com.example.clinicaocupacional.repository;

import clinica.com.example.clinicaocupacional.model.Personal;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalRepository extends CrudRepository<Personal, Integer> {
	@Query("select p from Personal p where p.numero_colegiatura = :numeroColegiatura")
	Optional<Personal> findByNumeroColegiatura(String numeroColegiatura);
	List<Personal> findByEspecialidad_Id(Integer especialidadId);
	Optional<Personal> findByUsuario_Id(Integer usuarioId);
}
