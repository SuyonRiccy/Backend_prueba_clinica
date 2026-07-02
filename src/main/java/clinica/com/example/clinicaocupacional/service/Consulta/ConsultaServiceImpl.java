package clinica.com.example.clinicaocupacional.service.Consulta;

import clinica.com.example.clinicaocupacional.dto.Consulta.ConsultaCreateDTO;
import clinica.com.example.clinicaocupacional.dto.Consulta.ConsultaResponseDTO;
import clinica.com.example.clinicaocupacional.dto.Consulta.ConsultaUpdateDTO;
import clinica.com.example.clinicaocupacional.dto.DetalleConsulta.DetalleConsultaResponseDTO;
import clinica.com.example.clinicaocupacional.dto.Documento.DocumentoResponseDTO;
import clinica.com.example.clinicaocupacional.dto.Especialidad.EspecialidadResponseDTO;
import clinica.com.example.clinicaocupacional.dto.Estado.EstadoResponseDTO;
import clinica.com.example.clinicaocupacional.dto.Personal.PersonalResponseDTO;
import clinica.com.example.clinicaocupacional.dto.Rol.RolResponseDTO;
import clinica.com.example.clinicaocupacional.dto.Usuario.UsuarioResponseDTO;
import clinica.com.example.clinicaocupacional.dto.Turno.TurnoResponseDTO;
import clinica.com.example.clinicaocupacional.exception.ResourceNotFoundException;
import clinica.com.example.clinicaocupacional.model.Consulta;
import clinica.com.example.clinicaocupacional.model.DetalleConsulta;
import clinica.com.example.clinicaocupacional.model.Documento;
import clinica.com.example.clinicaocupacional.model.Estado;
import clinica.com.example.clinicaocupacional.model.Especialidad;
import clinica.com.example.clinicaocupacional.model.Personal;
import clinica.com.example.clinicaocupacional.model.Rol;
import clinica.com.example.clinicaocupacional.model.Usuario;
import clinica.com.example.clinicaocupacional.model.Turno;
import clinica.com.example.clinicaocupacional.model.HorarioMes;
import clinica.com.example.clinicaocupacional.repository.ConsultaRepository;
import clinica.com.example.clinicaocupacional.repository.EstadoRepository;
import clinica.com.example.clinicaocupacional.repository.UsuarioRepository;
import clinica.com.example.clinicaocupacional.repository.EspecialidadRepository;
import clinica.com.example.clinicaocupacional.repository.DetalleConsultaRepository;
import clinica.com.example.clinicaocupacional.repository.TurnoRepository;
import clinica.com.example.clinicaocupacional.repository.HorarioMesRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ConsultaServiceImpl implements ConsultaService {

    private final ConsultaRepository consultaRepository;
    private final UsuarioRepository usuarioRepository;
    private final EstadoRepository estadoRepository;
    private final EspecialidadRepository especialidadRepository;
    private final DetalleConsultaRepository detalleConsultaRepository;
    private final TurnoRepository turnoRepository;
    private final HorarioMesRepository horarioMesRepository;

    @Override
    public ConsultaResponseDTO crear(ConsultaCreateDTO dto) {
        Consulta consulta = mapToEntity(dto);
        Consulta savedConsulta = consultaRepository.save(consulta);

        if (dto.getEspecialidadesIds() != null) {
            List<DetalleConsulta> detalles = new ArrayList<>();
            for (Integer espId : dto.getEspecialidadesIds()) {
                DetalleConsulta dc = new DetalleConsulta();
                dc.setConsulta(savedConsulta);
                dc.setEspecialidad(especialidadRepository.findById(espId)
                    .orElseThrow(() -> new ResourceNotFoundException("Especialidad", "id", espId)));
                dc.setEstado(savedConsulta.getEstado());
                dc.setResultados(null);
                
                // Asignar médico disponible para esta especialidad en el turno seleccionado
                if (savedConsulta.getTurno() != null) {
                    List<HorarioMes> list = StreamSupport.stream(horarioMesRepository.findAll().spliterator(), false)
                        .filter(hm -> hm.getHorario() != null && hm.getHorario().getTurno() != null &&
                                      hm.getHorario().getTurno().getId().equals(savedConsulta.getTurno().getId()) &&
                                      hm.getPersonal() != null && hm.getPersonal().getEspecialidad() != null &&
                                      hm.getPersonal().getEspecialidad().getId().equals(espId) &&
                                      hm.getPersonal().isVigente())
                        .collect(Collectors.toList());
                    if (!list.isEmpty()) {
                        dc.setMedico(list.get(0).getPersonal());
                    } else {
                        dc.setMedico(null);
                    }
                } else {
                    dc.setMedico(null);
                }
                
                detalles.add(detalleConsultaRepository.save(dc));
            }
            savedConsulta.setDetalleConsultas(detalles);
        }

        return mapToResponse(savedConsulta);
    }

    @Override
    public ConsultaResponseDTO actualizar(Integer id, ConsultaUpdateDTO dto) {
        Consulta consulta = consultaRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Consulta", "id", id));
        if (dto.getPacienteId() != null) {
            consulta.setPaciente(usuarioRepository.findById(dto.getPacienteId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario", "id", dto.getPacienteId())));
        }
        if (dto.getFechaConsulta() != null) {
            consulta.setFechaConsulta(dto.getFechaConsulta());
        }
        if (dto.getEstadoId() != null) {
            consulta.setEstado(estadoRepository.findById(dto.getEstadoId())
                .orElseThrow(() -> new ResourceNotFoundException("Estado", "id", dto.getEstadoId())));
        }
        if (dto.getTurnoId() != null) {
            consulta.setTurno(turnoRepository.findById(dto.getTurnoId())
                .orElseThrow(() -> new ResourceNotFoundException("Turno", "id", dto.getTurnoId())));
        }

        if (dto.getEspecialidadesIds() != null) {
            List<DetalleConsulta> existing = consulta.getDetalleConsultas();
            List<DetalleConsulta> toKeep = new ArrayList<>();
            List<DetalleConsulta> toDelete = new ArrayList<>();

            for (DetalleConsulta dc : existing) {
                if (dto.getEspecialidadesIds().contains(dc.getEspecialidad().getId())) {
                    toKeep.add(dc);
                } else {
                    toDelete.add(dc);
                }
            }

            detalleConsultaRepository.deleteAll(toDelete);

            for (Integer espId : dto.getEspecialidadesIds()) {
                boolean exists = toKeep.stream().anyMatch(dc -> dc.getEspecialidad().getId().equals(espId));
                if (!exists) {
                    DetalleConsulta dc = new DetalleConsulta();
                    dc.setConsulta(consulta);
                    dc.setEspecialidad(especialidadRepository.findById(espId)
                        .orElseThrow(() -> new ResourceNotFoundException("Especialidad", "id", espId)));
                    dc.setEstado(consulta.getEstado());
                    dc.setResultados(null);
                    
                    // Asignar médico disponible para esta especialidad en el turno seleccionado
                    if (consulta.getTurno() != null) {
                        List<HorarioMes> list = StreamSupport.stream(horarioMesRepository.findAll().spliterator(), false)
                            .filter(hm -> hm.getHorario() != null && hm.getHorario().getTurno() != null &&
                                          hm.getHorario().getTurno().getId().equals(consulta.getTurno().getId()) &&
                                          hm.getPersonal() != null && hm.getPersonal().getEspecialidad() != null &&
                                          hm.getPersonal().getEspecialidad().getId().equals(espId) &&
                                          hm.getPersonal().isVigente())
                            .collect(Collectors.toList());
                        if (!list.isEmpty()) {
                            dc.setMedico(list.get(0).getPersonal());
                        } else {
                            dc.setMedico(null);
                        }
                    } else {
                        dc.setMedico(null);
                    }
                    
                    toKeep.add(detalleConsultaRepository.save(dc));
                }
            }
            consulta.setDetalleConsultas(toKeep);
        }

        return mapToResponse(consultaRepository.save(consulta));
    }

    @Override
    public ConsultaResponseDTO obtenerPorId(Integer id) {
        return consultaRepository.findById(id)
            .map(this::mapToResponse)
            .orElseThrow(() -> new ResourceNotFoundException("Consulta", "id", id));
    }

    @Override
    public List<ConsultaResponseDTO> listarTodos() {
        return StreamSupport.stream(consultaRepository.findAll().spliterator(), false)
            .map(this::mapToResponse)
            .collect(Collectors.toList());
    }

    @Override
    public List<ConsultaResponseDTO> listarPorPaciente(Integer pacienteId) {
        return consultaRepository.findByPaciente_Id(pacienteId).stream()
            .map(this::mapToResponse)
            .collect(Collectors.toList());
    }

    @Override
    public void eliminar(Integer id) {
        Consulta consulta = consultaRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Consulta", "id", id));
        consultaRepository.delete(consulta);
    }

    private Consulta mapToEntity(ConsultaCreateDTO dto) {
        Consulta consulta = new Consulta();
        consulta.setPaciente(usuarioRepository.findById(dto.getPacienteId())
            .orElseThrow(() -> new ResourceNotFoundException("Usuario", "id", dto.getPacienteId())));
        consulta.setFechaConsulta(dto.getFechaConsulta());
        
        if (dto.getTurnoId() != null) {
            consulta.setTurno(turnoRepository.findById(dto.getTurnoId())
                .orElseThrow(() -> new ResourceNotFoundException("Turno", "id", dto.getTurnoId())));
        }

        if (dto.getEstadoId() != null) {
            consulta.setEstado(estadoRepository.findById(dto.getEstadoId())
                .orElseThrow(() -> new ResourceNotFoundException("Estado", "id", dto.getEstadoId())));
        } else {
            List<Estado> allEstados = StreamSupport.stream(estadoRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
            Estado enCurso = allEstados.stream()
                .filter(e -> e.getNombre().equalsIgnoreCase("En curso"))
                .findFirst()
                .orElseGet(() -> allEstados.stream().filter(e -> e.getNombre().equalsIgnoreCase("Pendiente")).findFirst()
                .orElse(allEstados.isEmpty() ? null : allEstados.get(0)));
            if (enCurso == null) {
                throw new ResourceNotFoundException("Estado por defecto 'En curso' o 'Pendiente' no existe.");
            }
            consulta.setEstado(enCurso);
        }

        return consulta;
    }

    private ConsultaResponseDTO mapToResponse(Consulta consulta) {
        ConsultaResponseDTO response = new ConsultaResponseDTO();
        response.setId(consulta.getId());
        response.setPaciente(consulta.getPaciente() != null ? mapUsuarioShallow(consulta.getPaciente()) : null);
        response.setFechaConsulta(consulta.getFechaConsulta());
        response.setEstado(consulta.getEstado() != null ? mapEstadoShallow(consulta.getEstado()) : null);
        response.setTurno(consulta.getTurno() != null ? mapTurnoShallow(consulta.getTurno()) : null);
        response.setDetalleConsultas(consulta.getDetalleConsultas() == null ? null : consulta.getDetalleConsultas().stream()
            .map(this::mapDetalleConsultaShallow)
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
        response.setDocumento(usuario.getDocumento() != null ? mapDocumentoShallow(usuario.getDocumento()) : null);
        response.setNumeroDocumento(usuario.getNumeroDocumento());
        response.setTelefono(usuario.getTelefono());
        response.setRol(usuario.getRol() != null ? mapRolShallow(usuario.getRol()) : null);
        return response;
    }

    private DocumentoResponseDTO mapDocumentoShallow(Documento documento) {
        DocumentoResponseDTO response = new DocumentoResponseDTO();
        response.setId(documento.getId());
        response.setNombre(documento.getNombre());
        response.setVigente(documento.isVigente());
        return response;
    }

    private RolResponseDTO mapRolShallow(Rol rol) {
        RolResponseDTO response = new RolResponseDTO();
        response.setId(rol.getId());
        response.setNombre(rol.getNombre());
        response.setVigente(rol.isVigente());
        return response;
    }

    private EstadoResponseDTO mapEstadoShallow(Estado estado) {
        EstadoResponseDTO response = new EstadoResponseDTO();
        response.setId(estado.getId());
        response.setNombre(estado.getNombre());
        response.setVigente(estado.isVigente());
        response.setConsultas(null);
        response.setDetalleConsultas(null);
        return response;
    }

    private TurnoResponseDTO mapTurnoShallow(Turno turno) {
        TurnoResponseDTO response = new TurnoResponseDTO();
        response.setId(turno.getId());
        response.setNombre(turno.getNombre());
        response.setVigente(turno.isVigente());
        response.setHorarios(null);
        return response;
    }

    private DetalleConsultaResponseDTO mapDetalleConsultaShallow(DetalleConsulta detalleConsulta) {
        DetalleConsultaResponseDTO response = new DetalleConsultaResponseDTO();
        response.setId(detalleConsulta.getId());
        response.setConsulta(null);
        response.setMedico(detalleConsulta.getMedico() != null ? mapPersonalShallow(detalleConsulta.getMedico()) : null);
        response.setEstado(detalleConsulta.getEstado() != null ? mapEstadoShallow(detalleConsulta.getEstado()) : null);
        response.setResultados(detalleConsulta.getResultados());
        response.setEspecialidad(detalleConsulta.getEspecialidad() != null ? mapEspecialidadShallow(detalleConsulta.getEspecialidad()) : null);
        return response;
    }

    private PersonalResponseDTO mapPersonalShallow(Personal personal) {
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
        response.setHorarioMeses(null);
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
}
