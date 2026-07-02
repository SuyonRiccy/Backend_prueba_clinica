package clinica.com.example.clinicaocupacional.service.DetalleConsulta;

import clinica.com.example.clinicaocupacional.dto.DetalleConsulta.DetalleConsultaCreateDTO;
import clinica.com.example.clinicaocupacional.dto.DetalleConsulta.DetalleConsultaResponseDTO;
import clinica.com.example.clinicaocupacional.dto.DetalleConsulta.DetalleConsultaUpdateDTO;
import java.util.List;

public interface DetalleConsultaService {
    DetalleConsultaResponseDTO crear(DetalleConsultaCreateDTO dto);
    DetalleConsultaResponseDTO actualizar(Integer id, DetalleConsultaUpdateDTO dto);
    DetalleConsultaResponseDTO obtenerPorId(Integer id);
    List<DetalleConsultaResponseDTO> listarTodos();
    List<DetalleConsultaResponseDTO> listarPorConsulta(Integer consultaId);
    void eliminar(Integer id);
}
