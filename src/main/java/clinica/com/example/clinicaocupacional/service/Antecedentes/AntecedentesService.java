package clinica.com.example.clinicaocupacional.service.Antecedentes;

import clinica.com.example.clinicaocupacional.dto.Antecedentes.AntecedentesCreateDTO;
import clinica.com.example.clinicaocupacional.dto.Antecedentes.AntecedentesResponseDTO;
import clinica.com.example.clinicaocupacional.dto.Antecedentes.AntecedentesUpdateDTO;
import java.util.List;

public interface AntecedentesService {
    AntecedentesResponseDTO crear(AntecedentesCreateDTO dto);
    AntecedentesResponseDTO actualizar(Integer id, AntecedentesUpdateDTO dto);
    AntecedentesResponseDTO obtenerPorId(Integer id);
    List<AntecedentesResponseDTO> listarTodos();
    List<AntecedentesResponseDTO> listarPorPaciente(Integer pacienteId);
    void eliminar(Integer id);
}
