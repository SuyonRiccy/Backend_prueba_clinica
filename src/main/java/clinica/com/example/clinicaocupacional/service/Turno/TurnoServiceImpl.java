package clinica.com.example.clinicaocupacional.service.Turno;

import clinica.com.example.clinicaocupacional.dto.Turno.TurnoCreateDTO;
import clinica.com.example.clinicaocupacional.dto.Turno.TurnoResponseDTO;
import clinica.com.example.clinicaocupacional.dto.Turno.TurnoUpdateDTO;
import clinica.com.example.clinicaocupacional.exception.ResourceNotFoundException;
import clinica.com.example.clinicaocupacional.model.Turno;
import clinica.com.example.clinicaocupacional.repository.TurnoRepository;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TurnoServiceImpl implements TurnoService {

    private final TurnoRepository turnoRepository;

    @Override
    public TurnoResponseDTO crear(TurnoCreateDTO dto) {
        Turno turno = mapToEntity(dto);
        turno.setVigente(true);
        return mapToResponse(turnoRepository.save(turno));
    }

    @Override
    public TurnoResponseDTO actualizar(Integer id, TurnoUpdateDTO dto) {
        Turno turno = turnoRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Turno", "id", id));
        if (dto.getNombre() != null) {
            turno.setNombre(dto.getNombre());
        }
        if (dto.getVigente() != null) {
            turno.setVigente(dto.getVigente());
        }
        return mapToResponse(turnoRepository.save(turno));
    }

    @Override
    public TurnoResponseDTO obtenerPorId(Integer id) {
        return turnoRepository.findById(id)
            .map(this::mapToResponse)
            .orElseThrow(() -> new ResourceNotFoundException("Turno", "id", id));
    }

    @Override
    public List<TurnoResponseDTO> listarTodos() {
        return StreamSupport.stream(turnoRepository.findAll().spliterator(), false)
            .map(this::mapToResponse)
            .collect(Collectors.toList());
    }

    @Override
    public List<TurnoResponseDTO> listarActivos() {
        return StreamSupport.stream(turnoRepository.findAll().spliterator(), false)
            .filter(Turno::isVigente)
            .map(this::mapToResponse)
            .collect(Collectors.toList());
    }

    @Override
    public void eliminar(Integer id) {
        Turno turno = turnoRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Turno", "id", id));
        turno.setVigente(false);
        turnoRepository.save(turno);
    }

    private Turno mapToEntity(TurnoCreateDTO dto) {
        Turno turno = new Turno();
        turno.setNombre(dto.getNombre());
        return turno;
    }

    private TurnoResponseDTO mapToResponse(Turno turno) {
        TurnoResponseDTO response = new TurnoResponseDTO();
        response.setId(turno.getId());
        response.setNombre(turno.getNombre());
        response.setVigente(turno.isVigente());
        return response;
    }
}
