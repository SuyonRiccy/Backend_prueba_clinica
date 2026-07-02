package clinica.com.example.clinicaocupacional.service.Horario;

import clinica.com.example.clinicaocupacional.dto.Horario.HorarioCreateDTO;
import clinica.com.example.clinicaocupacional.dto.Horario.HorarioResponseDTO;
import clinica.com.example.clinicaocupacional.dto.Horario.HorarioUpdateDTO;
import java.util.List;

public interface HorarioService {
    HorarioResponseDTO crear(HorarioCreateDTO dto);
    HorarioResponseDTO actualizar(Integer id, HorarioUpdateDTO dto);
    HorarioResponseDTO obtenerPorId(Integer id);
    List<HorarioResponseDTO> listarTodos();
    void eliminar(Integer id);
}
