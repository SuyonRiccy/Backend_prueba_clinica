package clinica.com.example.clinicaocupacional.service.HorarioMes;

import clinica.com.example.clinicaocupacional.dto.HorarioMes.HorarioMesCreateDTO;
import clinica.com.example.clinicaocupacional.dto.HorarioMes.HorarioMesResponseDTO;
import clinica.com.example.clinicaocupacional.dto.HorarioMes.HorarioMesUpdateDTO;
import java.util.List;

public interface HorarioMesService {
    HorarioMesResponseDTO crear(HorarioMesCreateDTO dto);
    HorarioMesResponseDTO actualizar(Integer id, HorarioMesUpdateDTO dto);
    HorarioMesResponseDTO obtenerPorId(Integer id);
    List<HorarioMesResponseDTO> listarTodos();
    List<HorarioMesResponseDTO> listarPorPersonal(Integer personalId);
    void eliminar(Integer id);
}
