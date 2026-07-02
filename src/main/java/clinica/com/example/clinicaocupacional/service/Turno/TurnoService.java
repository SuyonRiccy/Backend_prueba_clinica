package clinica.com.example.clinicaocupacional.service.Turno;

import clinica.com.example.clinicaocupacional.dto.Turno.TurnoCreateDTO;
import clinica.com.example.clinicaocupacional.dto.Turno.TurnoResponseDTO;
import clinica.com.example.clinicaocupacional.dto.Turno.TurnoUpdateDTO;
import java.util.List;

public interface TurnoService {
    TurnoResponseDTO crear(TurnoCreateDTO dto);
    TurnoResponseDTO actualizar(Integer id, TurnoUpdateDTO dto);
    TurnoResponseDTO obtenerPorId(Integer id);
    List<TurnoResponseDTO> listarTodos();
    List<TurnoResponseDTO> listarActivos();
    void eliminar(Integer id);
}
