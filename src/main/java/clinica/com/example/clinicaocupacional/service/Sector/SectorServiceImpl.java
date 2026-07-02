package clinica.com.example.clinicaocupacional.service.Sector;

import clinica.com.example.clinicaocupacional.dto.Sector.SectorCreateDTO;
import clinica.com.example.clinicaocupacional.dto.Sector.SectorResponseDTO;
import clinica.com.example.clinicaocupacional.dto.Sector.SectorUpdateDTO;
import clinica.com.example.clinicaocupacional.exception.ResourceNotFoundException;
import clinica.com.example.clinicaocupacional.model.Sector;
import clinica.com.example.clinicaocupacional.repository.SectorRepository;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SectorServiceImpl implements SectorService {

    private final SectorRepository sectorRepository;

    @Override
    public SectorResponseDTO crear(SectorCreateDTO dto) {
        Sector sector = mapToEntity(dto);
        sector.setVigente(true);
        return mapToResponse(sectorRepository.save(sector));
    }

    @Override
    public SectorResponseDTO actualizar(Integer id, SectorUpdateDTO dto) {
        Sector sector = sectorRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Sector", "id", id));
        if (dto.getNombre() != null) {
            sector.setNombre(dto.getNombre());
        }
        if (dto.getVigente() != null) {
            sector.setVigente(dto.getVigente());
        }
        return mapToResponse(sectorRepository.save(sector));
    }

    @Override
    public SectorResponseDTO obtenerPorId(Integer id) {
        return sectorRepository.findById(id)
            .map(this::mapToResponse)
            .orElseThrow(() -> new ResourceNotFoundException("Sector", "id", id));
    }

    @Override
    public List<SectorResponseDTO> listarTodos() {
        return StreamSupport.stream(sectorRepository.findAll().spliterator(), false)
            .map(this::mapToResponse)
            .collect(Collectors.toList());
    }

    @Override
    public List<SectorResponseDTO> listarActivos() {
        return StreamSupport.stream(sectorRepository.findAll().spliterator(), false)
            .filter(Sector::isVigente)
            .map(this::mapToResponse)
            .collect(Collectors.toList());
    }

    @Override
    public void eliminar(Integer id) {
        Sector sector = sectorRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Sector", "id", id));
        sector.setVigente(false);
        sectorRepository.save(sector);
    }

    private Sector mapToEntity(SectorCreateDTO dto) {
        Sector sector = new Sector();
        sector.setNombre(dto.getNombre());
        return sector;
    }

    private SectorResponseDTO mapToResponse(Sector sector) {
        SectorResponseDTO response = new SectorResponseDTO();
        response.setId(sector.getId());
        response.setNombre(sector.getNombre());
        response.setVigente(sector.isVigente());
        return response;
    }
}
