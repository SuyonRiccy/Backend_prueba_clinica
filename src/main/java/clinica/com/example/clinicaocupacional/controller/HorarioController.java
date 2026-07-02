package clinica.com.example.clinicaocupacional.controller;

import clinica.com.example.clinicaocupacional.dto.Horario.HorarioCreateDTO;
import clinica.com.example.clinicaocupacional.dto.Horario.HorarioResponseDTO;
import clinica.com.example.clinicaocupacional.dto.Horario.HorarioUpdateDTO;
import clinica.com.example.clinicaocupacional.service.Horario.HorarioService;
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
@RequestMapping("/api/horarios")
@RequiredArgsConstructor
public class HorarioController {

    private final HorarioService horarioService;

    @PostMapping
    public ResponseEntity<HorarioResponseDTO> crear(@Valid @RequestBody HorarioCreateDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(horarioService.crear(dto));
    }

    @GetMapping
    public ResponseEntity<List<HorarioResponseDTO>> listarTodos() {
        return ResponseEntity.ok(horarioService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HorarioResponseDTO> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(horarioService.obtenerPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<HorarioResponseDTO> actualizar(@PathVariable Integer id, @Valid @RequestBody HorarioUpdateDTO dto) {
        return ResponseEntity.ok(horarioService.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        horarioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}