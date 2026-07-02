package clinica.com.example.clinicaocupacional.service.Horario;

import clinica.com.example.clinicaocupacional.dto.Contrato.ContratoResponseDTO;
import clinica.com.example.clinicaocupacional.dto.Horario.HorarioCreateDTO;
import clinica.com.example.clinicaocupacional.dto.Horario.HorarioResponseDTO;
import clinica.com.example.clinicaocupacional.dto.Horario.HorarioUpdateDTO;
import clinica.com.example.clinicaocupacional.exception.ResourceNotFoundException;
import clinica.com.example.clinicaocupacional.dto.Turno.TurnoResponseDTO;
import clinica.com.example.clinicaocupacional.model.Contrato;
import clinica.com.example.clinicaocupacional.model.Horario;
import clinica.com.example.clinicaocupacional.model.Turno;
import clinica.com.example.clinicaocupacional.repository.ContratoRepository;
import clinica.com.example.clinicaocupacional.repository.HorarioRepository;
import clinica.com.example.clinicaocupacional.repository.TurnoRepository;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HorarioServiceImpl implements HorarioService {

    private final HorarioRepository horarioRepository;
    private final TurnoRepository turnoRepository;
    private final ContratoRepository contratoRepository;

    @Override
    public HorarioResponseDTO crear(HorarioCreateDTO dto) {
        Horario horario = mapToEntity(dto);
        return mapToResponse(horarioRepository.save(horario));
    }

    @Override
    public HorarioResponseDTO actualizar(Integer id, HorarioUpdateDTO dto) {
        Horario horario = horarioRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Horario", "id", id));
        if (dto.getHoraInicio() != null) {
            horario.setHora_inicio(dto.getHoraInicio());
        }
        if (dto.getHorarioFin() != null) {
            horario.setHorario_fin(dto.getHorarioFin());
        }
        if (dto.getTurnoId() != null) {
            horario.setTurno(turnoRepository.findById(dto.getTurnoId())
                .orElseThrow(() -> new ResourceNotFoundException("Turno", "id", dto.getTurnoId())));
        }
        if (dto.getContratoId() != null) {
            horario.setContrato(contratoRepository.findById(dto.getContratoId())
                .orElseThrow(() -> new ResourceNotFoundException("Contrato", "id", dto.getContratoId())));
        }
        return mapToResponse(horarioRepository.save(horario));
    }

    @Override
    public HorarioResponseDTO obtenerPorId(Integer id) {
        return horarioRepository.findById(id)
            .map(this::mapToResponse)
            .orElseThrow(() -> new ResourceNotFoundException("Horario", "id", id));
    }

    @Override
    public List<HorarioResponseDTO> listarTodos() {
        return StreamSupport.stream(horarioRepository.findAll().spliterator(), false)
            .map(this::mapToResponse)
            .collect(Collectors.toList());
    }

    @Override
    public void eliminar(Integer id) {
        Horario horario = horarioRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Horario", "id", id));
        horarioRepository.delete(horario);
    }

    private Horario mapToEntity(HorarioCreateDTO dto) {
        Horario horario = new Horario();
        horario.setHora_inicio(dto.getHoraInicio());
        horario.setHorario_fin(dto.getHorarioFin());
        if (dto.getTurnoId() != null) {
            horario.setTurno(turnoRepository.findById(dto.getTurnoId())
                .orElseThrow(() -> new ResourceNotFoundException("Turno", "id", dto.getTurnoId())));
        }
        if (dto.getContratoId() != null) {
            horario.setContrato(contratoRepository.findById(dto.getContratoId())
                .orElseThrow(() -> new ResourceNotFoundException("Contrato", "id", dto.getContratoId())));
        }
        return horario;
    }

    private HorarioResponseDTO mapToResponse(Horario horario) {
        HorarioResponseDTO response = new HorarioResponseDTO();
        response.setId(horario.getId());
        response.setHoraInicio(horario.getHora_inicio());
        response.setHorarioFin(horario.getHorario_fin());
        response.setTurno(horario.getTurno() != null ? mapTurnoShallow(horario.getTurno()) : null);
        response.setContrato(horario.getContrato() != null ? mapContratoShallow(horario.getContrato()) : null);
        return response;
    }

    private TurnoResponseDTO mapTurnoShallow(Turno turno) {
        TurnoResponseDTO response = new TurnoResponseDTO();
        response.setId(turno.getId());
        response.setNombre(turno.getNombre());
        response.setVigente(turno.isVigente());
        return response;
    }

    private ContratoResponseDTO mapContratoShallow(Contrato contrato) {
        ContratoResponseDTO response = new ContratoResponseDTO();
        response.setId(contrato.getId());
        response.setNombre(contrato.getNombre());
        response.setVigente(contrato.isVigente());
        return response;
    }
}
