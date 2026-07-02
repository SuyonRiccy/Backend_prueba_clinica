package clinica.com.example.clinicaocupacional.service.Empresa;

import clinica.com.example.clinicaocupacional.dto.Empresa.EmpresaCreateDTO;
import clinica.com.example.clinicaocupacional.dto.Empresa.EmpresaResponseDTO;
import clinica.com.example.clinicaocupacional.dto.Empresa.EmpresaUpdateDTO;
import java.util.List;

public interface EmpresaService {
    EmpresaResponseDTO crear(EmpresaCreateDTO dto);
    EmpresaResponseDTO actualizar(Integer id, EmpresaUpdateDTO dto);
    EmpresaResponseDTO obtenerPorId(Integer id);
    List<EmpresaResponseDTO> listarTodos();
    List<EmpresaResponseDTO> listarActivos();
    void eliminar(Integer id);
}
