package clinica.com.example.clinicaocupacional.controller;

import clinica.com.example.clinicaocupacional.dto.Estado.EstadoCreateDTO;
import clinica.com.example.clinicaocupacional.dto.Estado.EstadoResponseDTO;
import clinica.com.example.clinicaocupacional.dto.Estado.EstadoUpdateDTO;
import clinica.com.example.clinicaocupacional.service.Estado.EstadoService;
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
@RequestMapping("/api/estados")
@RequiredArgsConstructor
public class EstadoController {

    private final EstadoService estadoService;

    @PostMapping
    public ResponseEntity<EstadoResponseDTO> crear(@Valid @RequestBody EstadoCreateDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(estadoService.crear(dto));
    }

    @GetMapping
    public ResponseEntity<List<EstadoResponseDTO>> listarTodos() {
        return ResponseEntity.ok(estadoService.listarTodos());
    }

    @GetMapping("/activos")
    public ResponseEntity<List<EstadoResponseDTO>> listarActivos() {
        return ResponseEntity.ok(estadoService.listarActivos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstadoResponseDTO> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(estadoService.obtenerPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstadoResponseDTO> actualizar(@PathVariable Integer id, @Valid @RequestBody EstadoUpdateDTO dto) {
        return ResponseEntity.ok(estadoService.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        estadoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
