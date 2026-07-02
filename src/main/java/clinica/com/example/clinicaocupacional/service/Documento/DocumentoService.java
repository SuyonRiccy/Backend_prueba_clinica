package clinica.com.example.clinicaocupacional.service.Documento;

import clinica.com.example.clinicaocupacional.dto.Documento.DocumentoCreateDTO;
import clinica.com.example.clinicaocupacional.dto.Documento.DocumentoResponseDTO;
import clinica.com.example.clinicaocupacional.dto.Documento.DocumentoUpdateDTO;
import java.util.List;

public interface DocumentoService {
    DocumentoResponseDTO crear(DocumentoCreateDTO dto);
    DocumentoResponseDTO actualizar(Integer id, DocumentoUpdateDTO dto);
    DocumentoResponseDTO obtenerPorId(Integer id);
    List<DocumentoResponseDTO> listarTodos();
    List<DocumentoResponseDTO> listarActivos();
    void eliminar(Integer id);
}
