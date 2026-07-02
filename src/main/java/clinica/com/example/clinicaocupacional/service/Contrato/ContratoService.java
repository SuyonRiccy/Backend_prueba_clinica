package clinica.com.example.clinicaocupacional.service.Contrato;

import clinica.com.example.clinicaocupacional.dto.Contrato.ContratoCreateDTO;
import clinica.com.example.clinicaocupacional.dto.Contrato.ContratoResponseDTO;
import clinica.com.example.clinicaocupacional.dto.Contrato.ContratoUpdateDTO;
import java.util.List;

public interface ContratoService {
    ContratoResponseDTO crear(ContratoCreateDTO dto);
    ContratoResponseDTO actualizar(Integer id, ContratoUpdateDTO dto);
    ContratoResponseDTO obtenerPorId(Integer id);
    List<ContratoResponseDTO> listarTodos();
    List<ContratoResponseDTO> listarActivos();
    void eliminar(Integer id);
}
