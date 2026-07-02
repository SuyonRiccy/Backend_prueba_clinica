package clinica.com.example.clinicaocupacional.service.Usuario;

import clinica.com.example.clinicaocupacional.dto.Usuario.UsuarioCreateDTO;
import clinica.com.example.clinicaocupacional.dto.Usuario.UsuarioResponseDTO;
import clinica.com.example.clinicaocupacional.dto.Usuario.UsuarioUpdateDTO;
import java.util.List;

public interface UsuarioService {
    UsuarioResponseDTO crear(UsuarioCreateDTO dto);
    UsuarioResponseDTO actualizar(Integer id, UsuarioUpdateDTO dto);
    UsuarioResponseDTO obtenerPorId(Integer id);
    List<UsuarioResponseDTO> listarTodos();
    List<UsuarioResponseDTO> listarPorRol(Integer rolId);
    void eliminar(Integer id);
}
