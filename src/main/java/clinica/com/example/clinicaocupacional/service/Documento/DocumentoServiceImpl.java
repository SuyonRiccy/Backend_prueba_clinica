package clinica.com.example.clinicaocupacional.service.Documento;

import clinica.com.example.clinicaocupacional.dto.Documento.DocumentoCreateDTO;
import clinica.com.example.clinicaocupacional.dto.Documento.DocumentoResponseDTO;
import clinica.com.example.clinicaocupacional.dto.Documento.DocumentoUpdateDTO;
import clinica.com.example.clinicaocupacional.exception.ResourceNotFoundException;
import clinica.com.example.clinicaocupacional.model.Documento;
import clinica.com.example.clinicaocupacional.repository.DocumentoRepository;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DocumentoServiceImpl implements DocumentoService {

    private final DocumentoRepository documentoRepository;

    @Override
    public DocumentoResponseDTO crear(DocumentoCreateDTO dto) {
        Documento documento = mapToEntity(dto);
        documento.setVigente(true);
        return mapToResponse(documentoRepository.save(documento));
    }

    @Override
    public DocumentoResponseDTO actualizar(Integer id, DocumentoUpdateDTO dto) {
        Documento documento = documentoRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Documento", "id", id));
        if (dto.getNombre() != null) {
            documento.setNombre(dto.getNombre());
        }
        if (dto.getVigente() != null) {
            documento.setVigente(dto.getVigente());
        }
        return mapToResponse(documentoRepository.save(documento));
    }

    @Override
    public DocumentoResponseDTO obtenerPorId(Integer id) {
        return documentoRepository.findById(id)
            .map(this::mapToResponse)
            .orElseThrow(() -> new ResourceNotFoundException("Documento", "id", id));
    }

    @Override
    public List<DocumentoResponseDTO> listarTodos() {
        return StreamSupport.stream(documentoRepository.findAll().spliterator(), false)
            .map(this::mapToResponse)
            .collect(Collectors.toList());
    }

    @Override
    public List<DocumentoResponseDTO> listarActivos() {
        return StreamSupport.stream(documentoRepository.findAll().spliterator(), false)
            .filter(Documento::isVigente)
            .map(this::mapToResponse)
            .collect(Collectors.toList());
    }

    @Override
    public void eliminar(Integer id) {
        Documento documento = documentoRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Documento", "id", id));
        documento.setVigente(false);
        documentoRepository.save(documento);
    }

    private Documento mapToEntity(DocumentoCreateDTO dto) {
        Documento documento = new Documento();
        documento.setNombre(dto.getNombre());
        return documento;
    }

    private DocumentoResponseDTO mapToResponse(Documento documento) {
        DocumentoResponseDTO response = new DocumentoResponseDTO();
        response.setId(documento.getId());
        response.setNombre(documento.getNombre());
        response.setVigente(documento.isVigente());
        return response;
    }
}
