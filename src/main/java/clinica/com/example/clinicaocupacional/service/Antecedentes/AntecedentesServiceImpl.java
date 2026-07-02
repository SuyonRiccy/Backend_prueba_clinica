package clinica.com.example.clinicaocupacional.service.Antecedentes;

import clinica.com.example.clinicaocupacional.dto.Antecedentes.AntecedentesCreateDTO;
import clinica.com.example.clinicaocupacional.dto.Antecedentes.AntecedentesResponseDTO;
import clinica.com.example.clinicaocupacional.dto.Antecedentes.AntecedentesUpdateDTO;
import clinica.com.example.clinicaocupacional.dto.Documento.DocumentoResponseDTO;
import clinica.com.example.clinicaocupacional.exception.ResourceNotFoundException;
import clinica.com.example.clinicaocupacional.dto.Rol.RolResponseDTO;
import clinica.com.example.clinicaocupacional.dto.Usuario.UsuarioResponseDTO;
import clinica.com.example.clinicaocupacional.model.Antecedentes;
import clinica.com.example.clinicaocupacional.model.Documento;
import clinica.com.example.clinicaocupacional.model.Rol;
import clinica.com.example.clinicaocupacional.model.Usuario;
import clinica.com.example.clinicaocupacional.repository.AntecedentesRepository;
import clinica.com.example.clinicaocupacional.repository.UsuarioRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AntecedentesServiceImpl implements AntecedentesService {

    private final AntecedentesRepository antecedentesRepository;
    private final UsuarioRepository usuarioRepository;

    @Override
    public AntecedentesResponseDTO crear(AntecedentesCreateDTO dto) {
        Antecedentes antecedentes = mapToEntity(dto);
        antecedentes.setFechaRegistro(LocalDate.now());
        return mapToResponse(antecedentesRepository.save(antecedentes));
    }

    @Override
    public AntecedentesResponseDTO actualizar(Integer id, AntecedentesUpdateDTO dto) {
        Antecedentes antecedentes = antecedentesRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Antecedentes", "id", id));
        if (dto.getPacienteId() != null) {
            antecedentes.setPaciente(usuarioRepository.findById(dto.getPacienteId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario", "id", dto.getPacienteId())));
        }
        if (dto.getDescripcion() != null) {
            antecedentes.setDescripcion(dto.getDescripcion());
        }
        return mapToResponse(antecedentesRepository.save(antecedentes));
    }

    @Override
    public AntecedentesResponseDTO obtenerPorId(Integer id) {
        return antecedentesRepository.findById(id)
            .map(this::mapToResponse)
            .orElseThrow(() -> new ResourceNotFoundException("Antecedentes", "id", id));
    }

    @Override
    public List<AntecedentesResponseDTO> listarTodos() {
        return StreamSupport.stream(antecedentesRepository.findAll().spliterator(), false)
            .map(this::mapToResponse)
            .collect(Collectors.toList());
    }

    @Override
    public List<AntecedentesResponseDTO> listarPorPaciente(Integer pacienteId) {
        return antecedentesRepository.findByPaciente_Id(pacienteId).stream()
            .map(this::mapToResponse)
            .collect(Collectors.toList());
    }

    @Override
    public void eliminar(Integer id) {
        Antecedentes antecedentes = antecedentesRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Antecedentes", "id", id));
        antecedentesRepository.delete(antecedentes);
    }

    private Antecedentes mapToEntity(AntecedentesCreateDTO dto) {
        Antecedentes antecedentes = new Antecedentes();
        antecedentes.setPaciente(usuarioRepository.findById(dto.getPacienteId())
            .orElseThrow(() -> new ResourceNotFoundException("Usuario", "id", dto.getPacienteId())));
        antecedentes.setDescripcion(dto.getDescripcion());
        return antecedentes;
    }

    private AntecedentesResponseDTO mapToResponse(Antecedentes antecedentes) {
        AntecedentesResponseDTO response = new AntecedentesResponseDTO();
        response.setId(antecedentes.getId());
        response.setPaciente(antecedentes.getPaciente() != null ? mapUsuarioShallow(antecedentes.getPaciente()) : null);
        response.setDescripcion(antecedentes.getDescripcion());
        response.setFechaRegistro(antecedentes.getFechaRegistro());
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
}
