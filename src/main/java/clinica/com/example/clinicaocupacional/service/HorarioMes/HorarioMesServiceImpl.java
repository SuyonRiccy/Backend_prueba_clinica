package clinica.com.example.clinicaocupacional.service.HorarioMes;

import clinica.com.example.clinicaocupacional.dto.Horario.HorarioResponseDTO;
import clinica.com.example.clinicaocupacional.dto.HorarioMes.HorarioMesCreateDTO;
import clinica.com.example.clinicaocupacional.dto.HorarioMes.HorarioMesResponseDTO;
import clinica.com.example.clinicaocupacional.dto.HorarioMes.HorarioMesUpdateDTO;
import clinica.com.example.clinicaocupacional.exception.ResourceNotFoundException;
import clinica.com.example.clinicaocupacional.dto.Personal.PersonalResponseDTO;
import clinica.com.example.clinicaocupacional.model.Horario;
import clinica.com.example.clinicaocupacional.model.HorarioMes;
import clinica.com.example.clinicaocupacional.model.Personal;
import clinica.com.example.clinicaocupacional.repository.HorarioMesRepository;
import clinica.com.example.clinicaocupacional.repository.HorarioRepository;
import clinica.com.example.clinicaocupacional.repository.PersonalRepository;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class HorarioMesServiceImpl implements HorarioMesService {

    private final HorarioMesRepository horarioMesRepository;
    private final HorarioRepository horarioRepository;
    private final PersonalRepository personalRepository;

    @Override
    public HorarioMesResponseDTO crear(HorarioMesCreateDTO dto) {
        HorarioMes horarioMes = mapToEntity(dto);
        return mapToResponse(horarioMesRepository.save(horarioMes));
    }

    @Override
    public HorarioMesResponseDTO actualizar(Integer id, HorarioMesUpdateDTO dto) {
        HorarioMes horarioMes = horarioMesRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("HorarioMes", "id", id));
        if (dto.getHorarioId() != null) {
            horarioMes.setHorario(horarioRepository.findById(dto.getHorarioId())
                .orElseThrow(() -> new ResourceNotFoundException("Horario", "id", dto.getHorarioId())));
        }
        if (dto.getPersonalId() != null) {
            horarioMes.setPersonal(personalRepository.findById(dto.getPersonalId())
                .orElseThrow(() -> new ResourceNotFoundException("Personal", "id", dto.getPersonalId())));
        }
        return mapToResponse(horarioMesRepository.save(horarioMes));
    }

    @Override
    public HorarioMesResponseDTO obtenerPorId(Integer id) {
        return horarioMesRepository.findById(id)
            .map(this::mapToResponse)
            .orElseThrow(() -> new ResourceNotFoundException("HorarioMes", "id", id));
    }

    @Override
    public List<HorarioMesResponseDTO> listarTodos() {
        return StreamSupport.stream(horarioMesRepository.findAll().spliterator(), false)
            .map(this::mapToResponse)
            .collect(Collectors.toList());
    }

    @Override
    public List<HorarioMesResponseDTO> listarPorPersonal(Integer personalId) {
        return horarioMesRepository.findByPersonal_Id(personalId).stream()
            .map(this::mapToResponse)
            .collect(Collectors.toList());
    }

    @Override
    public void eliminar(Integer id) {
        HorarioMes horarioMes = horarioMesRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("HorarioMes", "id", id));
        horarioMesRepository.delete(horarioMes);
    }

    private HorarioMes mapToEntity(HorarioMesCreateDTO dto) {
        HorarioMes horarioMes = new HorarioMes();
        horarioMes.setHorario(horarioRepository.findById(dto.getHorarioId())
            .orElseThrow(() -> new ResourceNotFoundException("Horario", "id", dto.getHorarioId())));
        if (dto.getPersonalId() != null) {
            horarioMes.setPersonal(personalRepository.findById(dto.getPersonalId())
                .orElseThrow(() -> new ResourceNotFoundException("Personal", "id", dto.getPersonalId())));
        }
        return horarioMes;
    }

    private HorarioMesResponseDTO mapToResponse(HorarioMes horarioMes) {
        HorarioMesResponseDTO response = new HorarioMesResponseDTO();
        response.setId(horarioMes.getId());
        response.setHorario(horarioMes.getHorario() != null ? mapHorarioShallow(horarioMes.getHorario()) : null);
        response.setPersonal(horarioMes.getPersonal() != null ? mapPersonalShallow(horarioMes.getPersonal()) : null);
        return response;
    }

    private HorarioResponseDTO mapHorarioShallow(Horario horario) {
        HorarioResponseDTO response = new HorarioResponseDTO();
        response.setId(horario.getId());
        response.setHoraInicio(horario.getHora_inicio());
        response.setHorarioFin(horario.getHorario_fin());
        return response;
    }

    private PersonalResponseDTO mapPersonalShallow(Personal personal) {
        PersonalResponseDTO response = new PersonalResponseDTO();
        response.setId(personal.getId());
        response.setUsuario(null);
        response.setEspecialidad(null);
        response.setNumeroColegiatura(personal.getNumero_colegiatura());
        response.setNumeroEspecialidad(personal.getNumero_especialidad());
        response.setFirmaDigital(personal.getFirma_digital());
        response.setInicioContrato(personal.getInicio_contrato());
        response.setFinContrato(personal.getFin_contrato());
        response.setVigente(personal.isVigente());
        response.setHorarioMeses(null);
        return response;
    }
}
