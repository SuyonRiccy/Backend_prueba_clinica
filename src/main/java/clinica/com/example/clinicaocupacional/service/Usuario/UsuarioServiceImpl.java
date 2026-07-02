package clinica.com.example.clinicaocupacional.service.Usuario;

import clinica.com.example.clinicaocupacional.dto.Documento.DocumentoResponseDTO;
import clinica.com.example.clinicaocupacional.exception.ResourceNotFoundException;
import clinica.com.example.clinicaocupacional.dto.Rol.RolResponseDTO;
import clinica.com.example.clinicaocupacional.dto.Usuario.UsuarioCreateDTO;
import clinica.com.example.clinicaocupacional.dto.Usuario.UsuarioResponseDTO;
import clinica.com.example.clinicaocupacional.dto.Usuario.UsuarioUpdateDTO;
import clinica.com.example.clinicaocupacional.model.Documento;
import clinica.com.example.clinicaocupacional.model.Rol;
import clinica.com.example.clinicaocupacional.model.Usuario;
import clinica.com.example.clinicaocupacional.repository.DocumentoRepository;
import clinica.com.example.clinicaocupacional.repository.RolRepository;
import clinica.com.example.clinicaocupacional.repository.UsuarioRepository;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final DocumentoRepository documentoRepository;
    private final RolRepository rolRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UsuarioResponseDTO crear(UsuarioCreateDTO dto) {
        Usuario usuario = mapToEntity(dto);
        return mapToResponse(usuarioRepository.save(usuario));
    }

    @Override
    public UsuarioResponseDTO actualizar(Integer id, UsuarioUpdateDTO dto) {
        Usuario usuario = usuarioRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Usuario", "id", id));
        if (dto.getNombres() != null) {
            usuario.setNombres(dto.getNombres());
        }
        if (dto.getApellidoPaterno() != null) {
            usuario.setApellidoPaterno(dto.getApellidoPaterno());
        }
        if (dto.getApellidoMaterno() != null) {
            usuario.setApellidoMaterno(dto.getApellidoMaterno());
        }
        if (dto.getCorreoCoorporativo() != null) {
            usuario.setCorreoCoorporativo(dto.getCorreoCoorporativo());
        }
        if (dto.getContrasena() != null && !dto.getContrasena().isBlank()) {
            usuario.setContrasena(passwordEncoder.encode(dto.getContrasena()));
        }
        if (dto.getDocumentoId() != null) {
            usuario.setDocumento(documentoRepository.findById(dto.getDocumentoId())
                .orElseThrow(() -> new ResourceNotFoundException("Documento", "id", dto.getDocumentoId())));
        }
        if (dto.getNumeroDocumento() != null) {
            usuario.setNumeroDocumento(dto.getNumeroDocumento());
        }
        if (dto.getTelefono() != null) {
            usuario.setTelefono(dto.getTelefono());
        }
        if (dto.getRolId() != null) {
            usuario.setRol(rolRepository.findById(dto.getRolId())
                .orElseThrow(() -> new ResourceNotFoundException("Rol", "id", dto.getRolId())));
        }
        return mapToResponse(usuarioRepository.save(usuario));
    }

    @Override
    public UsuarioResponseDTO obtenerPorId(Integer id) {
        return usuarioRepository.findById(id)
            .map(this::mapToResponse)
            .orElseThrow(() -> new ResourceNotFoundException("Usuario", "id", id));
    }

    @Override
    public List<UsuarioResponseDTO> listarTodos() {
        return StreamSupport.stream(usuarioRepository.findAll().spliterator(), false)
            .map(this::mapToResponse)
            .collect(Collectors.toList());
    }

    @Override
    public List<UsuarioResponseDTO> listarPorRol(Integer rolId) {
        return usuarioRepository.findByRol_Id(rolId).stream()
            .map(this::mapToResponse)
            .collect(Collectors.toList());
    }

    @Override
    public void eliminar(Integer id) {
        Usuario usuario = usuarioRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Usuario", "id", id));
        usuarioRepository.delete(usuario);
    }

    private Usuario mapToEntity(UsuarioCreateDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setNombres(dto.getNombres());
        usuario.setApellidoPaterno(dto.getApellidoPaterno());
        usuario.setApellidoMaterno(dto.getApellidoMaterno());
        usuario.setCorreoCoorporativo(dto.getCorreoCoorporativo());
        usuario.setContrasena(passwordEncoder.encode(dto.getContrasena()));
        usuario.setDocumento(documentoRepository.findById(dto.getDocumentoId())
            .orElseThrow(() -> new ResourceNotFoundException("Documento", "id", dto.getDocumentoId())));
        usuario.setNumeroDocumento(dto.getNumeroDocumento());
        usuario.setTelefono(dto.getTelefono());
        usuario.setRol(rolRepository.findById(dto.getRolId())
            .orElseThrow(() -> new ResourceNotFoundException("Rol", "id", dto.getRolId())));
        return usuario;
    }

    private UsuarioResponseDTO mapToResponse(Usuario usuario) {
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
