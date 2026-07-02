package clinica.com.example.clinicaocupacional.service.Especialidad;

import clinica.com.example.clinicaocupacional.dto.Especialidad.EspecialidadCreateDTO;
import clinica.com.example.clinicaocupacional.dto.Especialidad.EspecialidadResponseDTO;
import clinica.com.example.clinicaocupacional.dto.Especialidad.EspecialidadUpdateDTO;
import clinica.com.example.clinicaocupacional.exception.ResourceNotFoundException;
import clinica.com.example.clinicaocupacional.model.Especialidad;
import clinica.com.example.clinicaocupacional.repository.EspecialidadRepository;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EspecialidadServiceImpl implements EspecialidadService {

    private final EspecialidadRepository especialidadRepository;

    @Override
    public EspecialidadResponseDTO crear(EspecialidadCreateDTO dto) {
        Especialidad especialidad = mapToEntity(dto);
        especialidad.setVigente(true);
        return mapToResponse(especialidadRepository.save(especialidad));
    }

    @Override
    public EspecialidadResponseDTO actualizar(Integer id, EspecialidadUpdateDTO dto) {
        Especialidad especialidad = especialidadRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Especialidad", "id", id));
        if (dto.getNombre() != null) {
            especialidad.setNombre(dto.getNombre());
        }
        if (dto.getVigente() != null) {
            especialidad.setVigente(dto.getVigente());
        }
        return mapToResponse(especialidadRepository.save(especialidad));
    }

    @Override
    public EspecialidadResponseDTO obtenerPorId(Integer id) {
        return especialidadRepository.findById(id)
            .map(this::mapToResponse)
            .orElseThrow(() -> new ResourceNotFoundException("Especialidad", "id", id));
    }

    @Override
    public List<EspecialidadResponseDTO> listarTodos() {
        return StreamSupport.stream(especialidadRepository.findAll().spliterator(), false)
            .map(this::mapToResponse)
            .collect(Collectors.toList());
    }

    @Override
    public List<EspecialidadResponseDTO> listarActivos() {
        return StreamSupport.stream(especialidadRepository.findAll().spliterator(), false)
            .filter(Especialidad::isVigente)
            .map(this::mapToResponse)
            .collect(Collectors.toList());
    }

    @Override
    public void eliminar(Integer id) {
        Especialidad especialidad = especialidadRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Especialidad", "id", id));
        especialidad.setVigente(false);
        especialidadRepository.save(especialidad);
    }

    private Especialidad mapToEntity(EspecialidadCreateDTO dto) {
        Especialidad especialidad = new Especialidad();
        especialidad.setNombre(dto.getNombre());
        return especialidad;
    }

    private EspecialidadResponseDTO mapToResponse(Especialidad especialidad) {
        EspecialidadResponseDTO response = new EspecialidadResponseDTO();
        response.setId(especialidad.getId());
        response.setNombre(especialidad.getNombre());
        response.setVigente(especialidad.isVigente());
        return response;
    }
}
