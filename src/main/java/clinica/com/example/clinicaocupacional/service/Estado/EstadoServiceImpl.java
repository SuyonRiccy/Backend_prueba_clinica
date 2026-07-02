package clinica.com.example.clinicaocupacional.service.Estado;

import clinica.com.example.clinicaocupacional.dto.Estado.EstadoCreateDTO;
import clinica.com.example.clinicaocupacional.dto.Estado.EstadoResponseDTO;
import clinica.com.example.clinicaocupacional.dto.Estado.EstadoUpdateDTO;
import clinica.com.example.clinicaocupacional.exception.ResourceNotFoundException;
import clinica.com.example.clinicaocupacional.model.Estado;
import clinica.com.example.clinicaocupacional.repository.EstadoRepository;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EstadoServiceImpl implements EstadoService {

    private final EstadoRepository estadoRepository;

    @Override
    public EstadoResponseDTO crear(EstadoCreateDTO dto) {
        Estado estado = mapToEntity(dto);
        estado.setVigente(true);
        return mapToResponse(estadoRepository.save(estado));
    }

    @Override
    public EstadoResponseDTO actualizar(Integer id, EstadoUpdateDTO dto) {
        Estado estado = estadoRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Estado", "id", id));
        if (dto.getNombre() != null) {
            estado.setNombre(dto.getNombre());
        }
        if (dto.getVigente() != null) {
            estado.setVigente(dto.getVigente());
        }
        return mapToResponse(estadoRepository.save(estado));
    }

    @Override
    public EstadoResponseDTO obtenerPorId(Integer id) {
        return estadoRepository.findById(id)
            .map(this::mapToResponse)
            .orElseThrow(() -> new ResourceNotFoundException("Estado", "id", id));
    }

    @Override
    public List<EstadoResponseDTO> listarTodos() {
        return StreamSupport.stream(estadoRepository.findAll().spliterator(), false)
            .map(this::mapToResponse)
            .collect(Collectors.toList());
    }

    @Override
    public List<EstadoResponseDTO> listarActivos() {
        return StreamSupport.stream(estadoRepository.findAll().spliterator(), false)
            .filter(Estado::isVigente)
            .map(this::mapToResponse)
            .collect(Collectors.toList());
    }

    @Override
    public void eliminar(Integer id) {
        Estado estado = estadoRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Estado", "id", id));
        estado.setVigente(false);
        estadoRepository.save(estado);
    }

    private Estado mapToEntity(EstadoCreateDTO dto) {
        Estado estado = new Estado();
        estado.setNombre(dto.getNombre());
        return estado;
    }

    private EstadoResponseDTO mapToResponse(Estado estado) {
        EstadoResponseDTO response = new EstadoResponseDTO();
        response.setId(estado.getId());
        response.setNombre(estado.getNombre());
        response.setVigente(estado.isVigente());
        return response;
    }
}
