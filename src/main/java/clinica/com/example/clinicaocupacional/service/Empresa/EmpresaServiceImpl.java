package clinica.com.example.clinicaocupacional.service.Empresa;

import clinica.com.example.clinicaocupacional.dto.Documento.DocumentoResponseDTO;
import clinica.com.example.clinicaocupacional.dto.Empresa.EmpresaCreateDTO;
import clinica.com.example.clinicaocupacional.dto.Empresa.EmpresaResponseDTO;
import clinica.com.example.clinicaocupacional.dto.Empresa.EmpresaUpdateDTO;
import clinica.com.example.clinicaocupacional.exception.ResourceNotFoundException;
import clinica.com.example.clinicaocupacional.dto.Sector.SectorResponseDTO;
import clinica.com.example.clinicaocupacional.dto.Usuario.UsuarioResponseDTO;
import clinica.com.example.clinicaocupacional.model.Documento;
import clinica.com.example.clinicaocupacional.model.Empresa;
import clinica.com.example.clinicaocupacional.model.Sector;
import clinica.com.example.clinicaocupacional.model.Usuario;
import clinica.com.example.clinicaocupacional.repository.DocumentoRepository;
import clinica.com.example.clinicaocupacional.repository.EmpresaRepository;
import clinica.com.example.clinicaocupacional.repository.SectorRepository;
import clinica.com.example.clinicaocupacional.repository.UsuarioRepository;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class EmpresaServiceImpl implements EmpresaService {

    private final EmpresaRepository empresaRepository;
    private final DocumentoRepository documentoRepository;
    private final SectorRepository sectorRepository;
    private final UsuarioRepository usuarioRepository;

    @Override
    public EmpresaResponseDTO crear(EmpresaCreateDTO dto) {
        Empresa empresa = mapToEntity(dto);
        empresa.setVigente(true);
        return mapToResponse(empresaRepository.save(empresa));
    }

    @Override
    public EmpresaResponseDTO actualizar(Integer id, EmpresaUpdateDTO dto) {
        Empresa empresa = empresaRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Empresa", "id", id));
        if (dto.getNombre() != null) {
            empresa.setNombre(dto.getNombre());
        }
        if (dto.getRazonSocial() != null) {
            empresa.setRazonSocial(dto.getRazonSocial());
        }
        if (dto.getDocumentoId() != null) {
            empresa.setDocumento(documentoRepository.findById(dto.getDocumentoId())
                .orElseThrow(() -> new ResourceNotFoundException("Documento", "id", dto.getDocumentoId())));
        }
        if (dto.getNumeroDocumento() != null) {
            empresa.setNumeroDocumento(dto.getNumeroDocumento());
        }
        if (dto.getSectorId() != null) {
            empresa.setSector(sectorRepository.findById(dto.getSectorId())
                .orElseThrow(() -> new ResourceNotFoundException("Sector", "id", dto.getSectorId())));
        }
        if (dto.getUsuarioCargoId() != null) {
            empresa.setUsuarioCargo(usuarioRepository.findById(dto.getUsuarioCargoId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario", "id", dto.getUsuarioCargoId())));
        }
        if (dto.getVigente() != null) {
            empresa.setVigente(dto.getVigente());
        }
        return mapToResponse(empresaRepository.save(empresa));
    }

    @Override
    public EmpresaResponseDTO obtenerPorId(Integer id) {
        return empresaRepository.findById(id)
            .map(this::mapToResponse)
            .orElseThrow(() -> new ResourceNotFoundException("Empresa", "id", id));
    }

    @Override
    public List<EmpresaResponseDTO> listarTodos() {
        return StreamSupport.stream(empresaRepository.findAll().spliterator(), false)
            .map(this::mapToResponse)
            .collect(Collectors.toList());
    }

    @Override
    public List<EmpresaResponseDTO> listarActivos() {
        return StreamSupport.stream(empresaRepository.findAll().spliterator(), false)
            .filter(Empresa::isVigente)
            .map(this::mapToResponse)
            .collect(Collectors.toList());
    }

    @Override
    public void eliminar(Integer id) {
        Empresa empresa = empresaRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Empresa", "id", id));
        empresa.setVigente(false);
        empresaRepository.save(empresa);
    }

    private Empresa mapToEntity(EmpresaCreateDTO dto) {
        Empresa empresa = new Empresa();
        empresa.setNombre(dto.getNombre());
        empresa.setRazonSocial(dto.getRazonSocial());
        empresa.setDocumento(documentoRepository.findById(dto.getDocumentoId())
            .orElseThrow(() -> new ResourceNotFoundException("Documento", "id", dto.getDocumentoId())));
        empresa.setNumeroDocumento(dto.getNumeroDocumento());
        empresa.setSector(sectorRepository.findById(dto.getSectorId())
            .orElseThrow(() -> new ResourceNotFoundException("Sector", "id", dto.getSectorId())));
        empresa.setUsuarioCargo(usuarioRepository.findById(dto.getUsuarioCargoId())
            .orElseThrow(() -> new ResourceNotFoundException("Usuario", "id", dto.getUsuarioCargoId())));
        return empresa;
    }

    private EmpresaResponseDTO mapToResponse(Empresa empresa) {
        EmpresaResponseDTO response = new EmpresaResponseDTO();
        response.setId(empresa.getId());
        response.setNombre(empresa.getNombre());
        response.setRazonSocial(empresa.getRazonSocial());
        response.setDocumento(empresa.getDocumento() != null ? mapDocumentoShallow(empresa.getDocumento()) : null);
        response.setNumeroDocumento(empresa.getNumeroDocumento());
        response.setSector(empresa.getSector() != null ? mapSectorShallow(empresa.getSector()) : null);
        response.setUsuarioCargo(empresa.getUsuarioCargo() != null ? mapUsuarioShallow(empresa.getUsuarioCargo()) : null);
        response.setVigente(empresa.isVigente());
        return response;
    }

    private DocumentoResponseDTO mapDocumentoShallow(Documento documento) {
        DocumentoResponseDTO response = new DocumentoResponseDTO();
        response.setId(documento.getId());
        response.setNombre(documento.getNombre());
        response.setVigente(documento.isVigente());
        return response;
    }

    private SectorResponseDTO mapSectorShallow(Sector sector) {
        SectorResponseDTO response = new SectorResponseDTO();
        response.setId(sector.getId());
        response.setNombre(sector.getNombre());
        response.setVigente(sector.isVigente());
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

    private clinica.com.example.clinicaocupacional.dto.Rol.RolResponseDTO mapRolShallow(clinica.com.example.clinicaocupacional.model.Rol rol) {
        clinica.com.example.clinicaocupacional.dto.Rol.RolResponseDTO response = new clinica.com.example.clinicaocupacional.dto.Rol.RolResponseDTO();
        response.setId(rol.getId());
        response.setNombre(rol.getNombre());
        response.setVigente(rol.isVigente());
        return response;
    }
}
