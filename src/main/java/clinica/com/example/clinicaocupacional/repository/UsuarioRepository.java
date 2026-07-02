package clinica.com.example.clinicaocupacional.repository;

import clinica.com.example.clinicaocupacional.model.Usuario;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {

    // Usar @Query con el nombre del campo Java exacto
    @Query("SELECT u FROM Usuario u WHERE u.correoCoorporativo = :correo")
    Optional<Usuario> findByCorreoCoorporativo(@Param("correo") String correo);

    List<Usuario> findByRol_Id(Integer rolId);
    List<Usuario> findByDocumento_Id(Integer documentoId);
}