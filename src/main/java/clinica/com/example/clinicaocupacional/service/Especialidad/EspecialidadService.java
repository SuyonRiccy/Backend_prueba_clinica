package clinica.com.example.clinicaocupacional.service.Especialidad;

import clinica.com.example.clinicaocupacional.dto.Especialidad.EspecialidadCreateDTO;
import clinica.com.example.clinicaocupacional.dto.Especialidad.EspecialidadResponseDTO;
import clinica.com.example.clinicaocupacional.dto.Especialidad.EspecialidadUpdateDTO;
import java.util.List;

public interface EspecialidadService {
    EspecialidadResponseDTO crear(EspecialidadCreateDTO dto);
    EspecialidadResponseDTO actualizar(Integer id, EspecialidadUpdateDTO dto);
    EspecialidadResponseDTO obtenerPorId(Integer id);
    List<EspecialidadResponseDTO> listarTodos();
    List<EspecialidadResponseDTO> listarActivos();
    void eliminar(Integer id);
}
