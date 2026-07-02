package clinica.com.example.clinicaocupacional.controller;

import clinica.com.example.clinicaocupacional.dto.Turno.TurnoCreateDTO;
import clinica.com.example.clinicaocupacional.dto.Turno.TurnoResponseDTO;
import clinica.com.example.clinicaocupacional.dto.Turno.TurnoUpdateDTO;
import clinica.com.example.clinicaocupacional.service.Turno.TurnoService;
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
@RequestMapping("/api/turnos")
@RequiredArgsConstructor
public class TurnoController {

    private final TurnoService turnoService;

    @PostMapping
    public ResponseEntity<TurnoResponseDTO> crear(@Valid @RequestBody TurnoCreateDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(turnoService.crear(dto));
    }

    @GetMapping
    public ResponseEntity<List<TurnoResponseDTO>> listarTodos() {
        return ResponseEntity.ok(turnoService.listarTodos());
    }

    @GetMapping("/activos")
    public ResponseEntity<List<TurnoResponseDTO>> listarActivos() {
        return ResponseEntity.ok(turnoService.listarActivos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TurnoResponseDTO> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(turnoService.obtenerPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TurnoResponseDTO> actualizar(@PathVariable Integer id, @Valid @RequestBody TurnoUpdateDTO dto) {
        return ResponseEntity.ok(turnoService.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        turnoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
