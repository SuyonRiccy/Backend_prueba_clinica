package clinica.com.example.clinicaocupacional.service.Personal;

import clinica.com.example.clinicaocupacional.dto.Especialidad.EspecialidadResponseDTO;
import clinica.com.example.clinicaocupacional.dto.Horario.HorarioResponseDTO;
import clinica.com.example.clinicaocupacional.dto.HorarioMes.HorarioMesResponseDTO;
import clinica.com.example.clinicaocupacional.dto.Personal.PersonalCreateDTO;
import clinica.com.example.clinicaocupacional.dto.Personal.PersonalResponseDTO;
import clinica.com.example.clinicaocupacional.dto.Personal.PersonalUpdateDTO;
import clinica.com.example.clinicaocupacional.exception.ResourceNotFoundException;
import clinica.com.example.clinicaocupacional.dto.Usuario.UsuarioResponseDTO;
import clinica.com.example.clinicaocupacional.model.Especialidad;
import clinica.com.example.clinicaocupacional.model.Horario;
import clinica.com.example.clinicaocupacional.model.HorarioMes;
import clinica.com.example.clinicaocupacional.model.Personal;
import clinica.com.example.clinicaocupacional.model.Usuario;
import clinica.com.example.clinicaocupacional.repository.EspecialidadRepository;
import clinica.com.example.clinicaocupacional.repository.HorarioMesRepository;
import clinica.com.example.clinicaocupacional.repository.PersonalRepository;
import clinica.com.example.clinicaocupacional.repository.UsuarioRepository;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PersonalServiceImpl implements PersonalService {

    private final PersonalRepository personalRepository;
    private final UsuarioRepository usuarioRepository;
    private final EspecialidadRepository especialidadRepository;
    private final HorarioMesRepository horarioMesRepository;

    @Override
    public PersonalResponseDTO crear(PersonalCreateDTO dto) {
        Personal personal = mapToEntity(dto);
        personal.setVigente(true);
        return mapToResponse(personalRepository.save(personal));
    }

    @Override
    public PersonalResponseDTO actualizar(Integer id, PersonalUpdateDTO dto) {
        Personal personal = personalRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Personal", "id", id));
        if (dto.getUsuarioId() != null) {
            personal.setUsuario(usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario", "id", dto.getUsuarioId())));
        }
        if (dto.getEspecialidadId() != null) {
            personal.setEspecialidad(especialidadRepository.findById(dto.getEspecialidadId())
                .orElseThrow(() -> new ResourceNotFoundException("Especialidad", "id", dto.getEspecialidadId())));
        }
        if (dto.getNumeroColegiatura() != null) {
            personal.setNumero_colegiatura(dto.getNumeroColegiatura());
        }
        if (dto.getNumeroEspecialidad() != null) {
            personal.setNumero_especialidad(dto.getNumeroEspecialidad());
        }
        if (dto.getFirmaDigital() != null) {
            personal.setFirma_digital(dto.getFirmaDigital());
        }
        if (dto.getInicioContrato() != null) {
            personal.setInicio_contrato(dto.getInicioContrato());
        }
        if (dto.getFinContrato() != null) {
            personal.setFin_contrato(dto.getFinContrato());
        }
        if (dto.getVigente() != null) {
            personal.setVigente(dto.getVigente());
        }
        return mapToResponse(personalRepository.save(personal));
    }

    @Override
    public PersonalResponseDTO obtenerPorId(Integer id) {
        return personalRepository.findById(id)
            .map(this::mapToResponse)
            .orElseThrow(() -> new ResourceNotFoundException("Personal", "id", id));
    }

    @Override
    public List<PersonalResponseDTO> listarTodos() {
        return StreamSupport.stream(personalRepository.findAll().spliterator(), false)
            .map(this::mapToResponse)
            .collect(Collectors.toList());
    }

    @Override
    public List<PersonalResponseDTO> listarActivos() {
        return StreamSupport.stream(personalRepository.findAll().spliterator(), false)
            .filter(Personal::isVigente)
            .map(this::mapToResponse)
            .collect(Collectors.toList());
    }

    @Override
    public List<PersonalResponseDTO> listarPorEspecialidad(Integer especialidadId) {
        return personalRepository.findByEspecialidad_Id(especialidadId).stream()
            .map(this::mapToResponse)
            .collect(Collectors.toList());
    }

    @Override
    public void eliminar(Integer id) {
        Personal personal = personalRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Personal", "id", id));
        personal.setVigente(false);
        personalRepository.save(personal);
    }

    private Personal mapToEntity(PersonalCreateDTO dto) {
        Personal personal = new Personal();
        if (dto.getUsuarioId() != null) {
            personal.setUsuario(usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario", "id", dto.getUsuarioId())));
        }
        if (dto.getEspecialidadId() != null) {
            personal.setEspecialidad(especialidadRepository.findById(dto.getEspecialidadId())
                .orElseThrow(() -> new ResourceNotFoundException("Especialidad", "id", dto.getEspecialidadId())));
        }
        personal.setNumero_colegiatura(dto.getNumeroColegiatura());
        personal.setNumero_especialidad(dto.getNumeroEspecialidad());
        personal.setFirma_digital(dto.getFirmaDigital());
        personal.setInicio_contrato(dto.getInicioContrato());
        personal.setFin_contrato(dto.getFinContrato());
        return personal;
    }

    private PersonalResponseDTO mapToResponse(Personal personal) {
        PersonalResponseDTO response = new PersonalResponseDTO();
        response.setId(personal.getId());
        response.setUsuario(personal.getUsuario() != null ? mapUsuarioShallow(personal.getUsuario()) : null);
        response.setEspecialidad(personal.getEspecialidad() != null ? mapEspecialidadShallow(personal.getEspecialidad()) : null);
        response.setNumeroColegiatura(personal.getNumero_colegiatura());
        response.setNumeroEspecialidad(personal.getNumero_especialidad());
        response.setFirmaDigital(personal.getFirma_digital());
        response.setInicioContrato(personal.getInicio_contrato());
        response.setFinContrato(personal.getFin_contrato());
        response.setVigente(personal.isVigente());
        response.setHorarioMeses(StreamSupport.stream(horarioMesRepository.findAll().spliterator(), false)
            .filter(horarioMes -> horarioMes.getPersonal() != null && Objects.equals(horarioMes.getPersonal().getId(), personal.getId()))
            .map(this::mapHorarioMesShallow)
            .collect(Collectors.toList()));
        return response;
    }

    private UsuarioResponseDTO mapUsuarioShallow(Usuario usuario) {
        UsuarioResponseDTO response = new UsuarioResponseDTO();
        response.setId(usuario.getId());
        response.setNombres(usuario.getNombres());
        response.setApellidoPaterno(usuario.getApellidoPaterno());
        response.setApellidoMaterno(usuario.getApellidoMaterno());
        response.setCorreoCoorporativo(usuario.getCorreoCoorporativo());
        response.setDocumento(null);
        response.setNumeroDocumento(usuario.getNumeroDocumento());
        response.setTelefono(usuario.getTelefono());
        response.setRol(null);
        return response;
    }

    private EspecialidadResponseDTO mapEspecialidadShallow(Especialidad especialidad) {
        EspecialidadResponseDTO response = new EspecialidadResponseDTO();
        response.setId(especialidad.getId());
        response.setNombre(especialidad.getNombre());
        response.setVigente(especialidad.isVigente());
        response.setPersonales(null);
        response.setDetalleConsultas(null);
        return response;
    }

    private HorarioMesResponseDTO mapHorarioMesShallow(HorarioMes horarioMes) {
        HorarioMesResponseDTO response = new HorarioMesResponseDTO();
        response.setId(horarioMes.getId());
        response.setHorario(horarioMes.getHorario() != null ? mapHorarioShallow(horarioMes.getHorario()) : null);
        response.setPersonal(null);
        return response;
    }

    private HorarioResponseDTO mapHorarioShallow(Horario horario) {
        HorarioResponseDTO response = new HorarioResponseDTO();
        response.setId(horario.getId());
        response.setHoraInicio(horario.getHora_inicio());
        response.setHorarioFin(horario.getHorario_fin());
        return response;
    }
}
