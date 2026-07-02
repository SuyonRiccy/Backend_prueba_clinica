package clinica.com.example.clinicaocupacional.service.Consulta;

import clinica.com.example.clinicaocupacional.dto.Consulta.ConsultaCreateDTO;
import clinica.com.example.clinicaocupacional.dto.Consulta.ConsultaResponseDTO;
import clinica.com.example.clinicaocupacional.dto.Consulta.ConsultaUpdateDTO;
import java.util.List;

public interface ConsultaService {
    ConsultaResponseDTO crear(ConsultaCreateDTO dto);
    ConsultaResponseDTO actualizar(Integer id, ConsultaUpdateDTO dto);
    ConsultaResponseDTO obtenerPorId(Integer id);
    List<ConsultaResponseDTO> listarTodos();
    List<ConsultaResponseDTO> listarPorPaciente(Integer pacienteId);
    void eliminar(Integer id);
}
