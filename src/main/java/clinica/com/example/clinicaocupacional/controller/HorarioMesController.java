package clinica.com.example.clinicaocupacional.controller;

import clinica.com.example.clinicaocupacional.dto.HorarioMes.HorarioMesCreateDTO;
import clinica.com.example.clinicaocupacional.dto.HorarioMes.HorarioMesResponseDTO;
import clinica.com.example.clinicaocupacional.dto.HorarioMes.HorarioMesUpdateDTO;
import clinica.com.example.clinicaocupacional.service.HorarioMes.HorarioMesService;
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
@RequestMapping("/api/horario-mes")
@RequiredArgsConstructor
public class HorarioMesController {

    private final HorarioMesService horarioMesService;

    @PostMapping
    public ResponseEntity<HorarioMesResponseDTO> crear(@Valid @RequestBody HorarioMesCreateDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(horarioMesService.crear(dto));
    }

    @GetMapping
    public ResponseEntity<List<HorarioMesResponseDTO>> listarTodos() {
        return ResponseEntity.ok(horarioMesService.listarTodos());
    }

    @GetMapping("/personal/{personalId}")
    public ResponseEntity<List<HorarioMesResponseDTO>> listarPorPersonal(@PathVariable Integer personalId) {
        return ResponseEntity.ok(horarioMesService.listarPorPersonal(personalId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<HorarioMesResponseDTO> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(horarioMesService.obtenerPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<HorarioMesResponseDTO> actualizar(@PathVariable Integer id, @Valid @RequestBody HorarioMesUpdateDTO dto) {
        return ResponseEntity.ok(horarioMesService.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        horarioMesService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
