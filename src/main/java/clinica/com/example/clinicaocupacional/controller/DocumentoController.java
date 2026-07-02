package clinica.com.example.clinicaocupacional.controller;

import clinica.com.example.clinicaocupacional.dto.Documento.DocumentoCreateDTO;
import clinica.com.example.clinicaocupacional.dto.Documento.DocumentoResponseDTO;
import clinica.com.example.clinicaocupacional.dto.Documento.DocumentoUpdateDTO;
import clinica.com.example.clinicaocupacional.service.Documento.DocumentoService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/documentos")
@RequiredArgsConstructor
public class DocumentoController {

    private final DocumentoService documentoService;

    @PostMapping
    public ResponseEntity<DocumentoResponseDTO> crear(@Valid @RequestBody DocumentoCreateDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(documentoService.crear(dto));
    }

    @GetMapping
    public ResponseEntity<List<DocumentoResponseDTO>> listarTodos() {
        return ResponseEntity.ok(documentoService.listarTodos());
    }

    @GetMapping("/activos")
    public ResponseEntity<List<DocumentoResponseDTO>> listarActivos() {
        return ResponseEntity.ok(documentoService.listarActivos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DocumentoResponseDTO> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(documentoService.obtenerPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DocumentoResponseDTO> actualizar(@PathVariable Integer id, @Valid @RequestBody DocumentoUpdateDTO dto) {
        return ResponseEntity.ok(documentoService.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        documentoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
