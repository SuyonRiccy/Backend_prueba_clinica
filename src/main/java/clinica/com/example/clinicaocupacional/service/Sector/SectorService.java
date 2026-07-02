package clinica.com.example.clinicaocupacional.service.Sector;

import clinica.com.example.clinicaocupacional.dto.Sector.SectorCreateDTO;
import clinica.com.example.clinicaocupacional.dto.Sector.SectorResponseDTO;
import clinica.com.example.clinicaocupacional.dto.Sector.SectorUpdateDTO;
import java.util.List;

public interface SectorService {
    SectorResponseDTO crear(SectorCreateDTO dto);
    SectorResponseDTO actualizar(Integer id, SectorUpdateDTO dto);
    SectorResponseDTO obtenerPorId(Integer id);
    List<SectorResponseDTO> listarTodos();
    List<SectorResponseDTO> listarActivos();
    void eliminar(Integer id);
}
