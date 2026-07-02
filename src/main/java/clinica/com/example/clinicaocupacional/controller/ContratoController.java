package clinica.com.example.clinicaocupacional.controller;

import clinica.com.example.clinicaocupacional.dto.Contrato.ContratoCreateDTO;
import clinica.com.example.clinicaocupacional.dto.Contrato.ContratoResponseDTO;
import clinica.com.example.clinicaocupacional.dto.Contrato.ContratoUpdateDTO;
import clinica.com.example.clinicaocupacional.service.Contrato.ContratoService;
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
@RequestMapping("/api/contratos")
@RequiredArgsConstructor
public class ContratoController {

    private final ContratoService contratoService;

    @PostMapping
    public ResponseEntity<ContratoResponseDTO> crear(@Valid @RequestBody ContratoCreateDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(contratoService.crear(dto));
    }

    @GetMapping
    public ResponseEntity<List<ContratoResponseDTO>> listarTodos() {
        return ResponseEntity.ok(contratoService.listarTodos());
    }

    @GetMapping("/activos")
    public ResponseEntity<List<ContratoResponseDTO>> listarActivos() {
        return ResponseEntity.ok(contratoService.listarActivos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContratoResponseDTO> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(contratoService.obtenerPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContratoResponseDTO> actualizar(@PathVariable Integer id, @Valid @RequestBody ContratoUpdateDTO dto) {
        return ResponseEntity.ok(contratoService.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        contratoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}