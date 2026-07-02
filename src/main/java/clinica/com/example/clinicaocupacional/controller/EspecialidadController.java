package clinica.com.example.clinicaocupacional.controller;

import clinica.com.example.clinicaocupacional.dto.Especialidad.EspecialidadCreateDTO;
import clinica.com.example.clinicaocupacional.dto.Especialidad.EspecialidadResponseDTO;
import clinica.com.example.clinicaocupacional.dto.Especialidad.EspecialidadUpdateDTO;
import clinica.com.example.clinicaocupacional.service.Especialidad.EspecialidadService;
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
@RequestMapping("/api/especialidades")
@RequiredArgsConstructor
public class EspecialidadController {

    private final EspecialidadService especialidadService;

    @PostMapping
    public ResponseEntity<EspecialidadResponseDTO> crear(@Valid @RequestBody EspecialidadCreateDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(especialidadService.crear(dto));
    }

    @GetMapping
    public ResponseEntity<List<EspecialidadResponseDTO>> listarTodos() {
        return ResponseEntity.ok(especialidadService.listarTodos());
    }

    @GetMapping("/activos")
    public ResponseEntity<List<EspecialidadResponseDTO>> listarActivos() {
        return ResponseEntity.ok(especialidadService.listarActivos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EspecialidadResponseDTO> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(especialidadService.obtenerPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EspecialidadResponseDTO> actualizar(@PathVariable Integer id, @Valid @RequestBody EspecialidadUpdateDTO dto) {
        return ResponseEntity.ok(especialidadService.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        especialidadService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
