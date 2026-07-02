package clinica.com.example.clinicaocupacional.service.Estado;

import clinica.com.example.clinicaocupacional.dto.Estado.EstadoCreateDTO;
import clinica.com.example.clinicaocupacional.dto.Estado.EstadoResponseDTO;
import clinica.com.example.clinicaocupacional.dto.Estado.EstadoUpdateDTO;
import java.util.List;

public interface EstadoService {
    EstadoResponseDTO crear(EstadoCreateDTO dto);
    EstadoResponseDTO actualizar(Integer id, EstadoUpdateDTO dto);
    EstadoResponseDTO obtenerPorId(Integer id);
    List<EstadoResponseDTO> listarTodos();
    List<EstadoResponseDTO> listarActivos();
    void eliminar(Integer id);
}
