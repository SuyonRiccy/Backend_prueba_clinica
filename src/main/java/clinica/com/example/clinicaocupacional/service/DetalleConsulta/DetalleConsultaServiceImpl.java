package clinica.com.example.clinicaocupacional.service.DetalleConsulta;

import clinica.com.example.clinicaocupacional.dto.Consulta.ConsultaResponseDTO;
import clinica.com.example.clinicaocupacional.dto.DetalleConsulta.DetalleConsultaCreateDTO;
import clinica.com.example.clinicaocupacional.dto.DetalleConsulta.DetalleConsultaResponseDTO;
import clinica.com.example.clinicaocupacional.dto.DetalleConsulta.DetalleConsultaUpdateDTO;
import clinica.com.example.clinicaocupacional.dto.Documento.DocumentoResponseDTO;
import clinica.com.example.clinicaocupacional.dto.Especialidad.EspecialidadResponseDTO;
import clinica.com.example.clinicaocupacional.dto.Estado.EstadoResponseDTO;
import clinica.com.example.clinicaocupacional.exception.ResourceNotFoundException;
import clinica.com.example.clinicaocupacional.dto.Personal.PersonalResponseDTO;
import clinica.com.example.clinicaocupacional.dto.Rol.RolResponseDTO;
import clinica.com.example.clinicaocupacional.dto.Usuario.UsuarioResponseDTO;
import clinica.com.example.clinicaocupacional.model.Consulta;
import clinica.com.example.clinicaocupacional.model.DetalleConsulta;
import clinica.com.example.clinicaocupacional.model.Documento;
import clinica.com.example.clinicaocupacional.model.Especialidad;
import clinica.com.example.clinicaocupacional.model.Estado;
import clinica.com.example.clinicaocupacional.model.Personal;
import clinica.com.example.clinicaocupacional.model.Rol;
import clinica.com.example.clinicaocupacional.model.Usuario;
import clinica.com.example.clinicaocupacional.repository.ConsultaRepository;
import clinica.com.example.clinicaocupacional.repository.DetalleConsultaRepository;
import clinica.com.example.clinicaocupacional.repository.EstadoRepository;
import clinica.com.example.clinicaocupacional.repository.PersonalRepository;
import clinica.com.example.clinicaocupacional.repository.EspecialidadRepository;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class DetalleConsultaServiceImpl implements DetalleConsultaService {

    private final DetalleConsultaRepository detalleConsultaRepository;
    private final ConsultaRepository consultaRepository;
    private final PersonalRepository personalRepository;
    private final EstadoRepository estadoRepository;
    private final EspecialidadRepository especialidadRepository;

    @Override
    public DetalleConsultaResponseDTO crear(DetalleConsultaCreateDTO dto) {
        DetalleConsulta detalleConsulta = mapToEntity(dto);
        return mapToResponse(detalleConsultaRepository.save(detalleConsulta));
    }

    @Override
    public DetalleConsultaResponseDTO actualizar(Integer id, DetalleConsultaUpdateDTO dto) {
        DetalleConsulta detalleConsulta = detalleConsultaRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("DetalleConsulta", "id", id));
        if (dto.getConsultaId() != null) {
            detalleConsulta.setConsulta(consultaRepository.findById(dto.getConsultaId())
                .orElseThrow(() -> new ResourceNotFoundException("Consulta", "id", dto.getConsultaId())));
        }
        if (dto.getMedicoId() != null) {
            detalleConsulta.setMedico(personalRepository.findById(dto.getMedicoId())
                .orElseThrow(() -> new ResourceNotFoundException("Personal", "id", dto.getMedicoId())));
        } else if (dto.getMedicoId() == null) {
            detalleConsulta.setMedico(null);
        }
        if (dto.getEstadoId() != null) {
            detalleConsulta.setEstado(estadoRepository.findById(dto.getEstadoId())
                .orElseThrow(() -> new ResourceNotFoundException("Estado", "id", dto.getEstadoId())));
        }
        if (dto.getResultados() != null) {
            detalleConsulta.setResultados(dto.getResultados());
        }
        if (dto.getEspecialidadId() != null) {
            detalleConsulta.setEspecialidad(especialidadRepository.findById(dto.getEspecialidadId())
                .orElseThrow(() -> new ResourceNotFoundException("Especialidad", "id", dto.getEspecialidadId())));
        }
        return mapToResponse(detalleConsultaRepository.save(detalleConsulta));
    }

    @Override
    public DetalleConsultaResponseDTO obtenerPorId(Integer id) {
        return detalleConsultaRepository.findById(id)
            .map(this::mapToResponse)
            .orElseThrow(() -> new ResourceNotFoundException("DetalleConsulta", "id", id));
    }

    @Override
    public List<DetalleConsultaResponseDTO> listarTodos() {
        return StreamSupport.stream(detalleConsultaRepository.findAll().spliterator(), false)
            .map(this::mapToResponse)
            .collect(Collectors.toList());
    }

    @Override
    public List<DetalleConsultaResponseDTO> listarPorConsulta(Integer consultaId) {
        return detalleConsultaRepository.findByConsulta_Id(consultaId).stream()
            .map(this::mapToResponse)
            .collect(Collectors.toList());
    }

    @Override
    public void eliminar(Integer id) {
        DetalleConsulta detalleConsulta = detalleConsultaRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("DetalleConsulta", "id", id));
        detalleConsultaRepository.delete(detalleConsulta);
    }

    private DetalleConsulta mapToEntity(DetalleConsultaCreateDTO dto) {
        DetalleConsulta detalleConsulta = new DetalleConsulta();
        detalleConsulta.setConsulta(consultaRepository.findById(dto.getConsultaId())
            .orElseThrow(() -> new ResourceNotFoundException("Consulta", "id", dto.getConsultaId())));
        if (dto.getMedicoId() != null) {
            detalleConsulta.setMedico(personalRepository.findById(dto.getMedicoId())
                .orElseThrow(() -> new ResourceNotFoundException("Personal", "id", dto.getMedicoId())));
        } else {
            detalleConsulta.setMedico(null);
        }
        detalleConsulta.setEstado(estadoRepository.findById(dto.getEstadoId())
            .orElseThrow(() -> new ResourceNotFoundException("Estado", "id", dto.getEstadoId())));
        detalleConsulta.setResultados(dto.getResultados());
        detalleConsulta.setEspecialidad(especialidadRepository.findById(dto.getEspecialidadId())
            .orElseThrow(() -> new ResourceNotFoundException("Especialidad", "id", dto.getEspecialidadId())));
        return detalleConsulta;
    }

    private DetalleConsultaResponseDTO mapToResponse(DetalleConsulta detalleConsulta) {
        DetalleConsultaResponseDTO response = new DetalleConsultaResponseDTO();
        response.setId(detalleConsulta.getId());
        response.setConsulta(detalleConsulta.getConsulta() != null ? mapConsultaShallow(detalleConsulta.getConsulta()) : null);
        response.setMedico(detalleConsulta.getMedico() != null ? mapPersonalShallow(detalleConsulta.getMedico()) : null);
        response.setEstado(detalleConsulta.getEstado() != null ? mapEstadoShallow(detalleConsulta.getEstado()) : null);
        response.setResultados(detalleConsulta.getResultados());
        response.setEspecialidad(detalleConsulta.getEspecialidad() != null ? mapEspecialidadShallow(detalleConsulta.getEspecialidad()) : null);
        return response;
    }

    private ConsultaResponseDTO mapConsultaShallow(Consulta consulta) {
        ConsultaResponseDTO response = new ConsultaResponseDTO();
        response.setId(consulta.getId());
        response.setPaciente(consulta.getPaciente() != null ? mapUsuarioShallow(consulta.getPaciente()) : null);
        response.setFechaConsulta(consulta.getFechaConsulta());
        response.setEstado(consulta.getEstado() != null ? mapEstadoShallow(consulta.getEstado()) : null);
        response.setDetalleConsultas(null);
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

    private EstadoResponseDTO mapEstadoShallow(Estado estado) {
        EstadoResponseDTO response = new EstadoResponseDTO();
        response.setId(estado.getId());
        response.setNombre(estado.getNombre());
        response.setVigente(estado.isVigente());
        response.setConsultas(null);
        response.setDetalleConsultas(null);
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
}
