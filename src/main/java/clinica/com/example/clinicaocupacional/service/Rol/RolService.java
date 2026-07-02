package clinica.com.example.clinicaocupacional.service.Rol;

import clinica.com.example.clinicaocupacional.dto.Rol.RolCreateDTO;
import clinica.com.example.clinicaocupacional.dto.Rol.RolResponseDTO;
import clinica.com.example.clinicaocupacional.dto.Rol.RolUpdateDTO;
import java.util.List;

public interface RolService {
    RolResponseDTO crear(RolCreateDTO dto);
    RolResponseDTO actualizar(Integer id, RolUpdateDTO dto);
    RolResponseDTO obtenerPorId(Integer id);
    List<RolResponseDTO> listarTodos();
    List<RolResponseDTO> listarActivos();
    void eliminar(Integer id);
}
