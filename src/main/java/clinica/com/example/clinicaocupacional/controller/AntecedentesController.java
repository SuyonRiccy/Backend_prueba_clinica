package clinica.com.example.clinicaocupacional.controller;

import clinica.com.example.clinicaocupacional.dto.Antecedentes.AntecedentesCreateDTO;
import clinica.com.example.clinicaocupacional.dto.Antecedentes.AntecedentesResponseDTO;
import clinica.com.example.clinicaocupacional.dto.Antecedentes.AntecedentesUpdateDTO;
import clinica.com.example.clinicaocupacional.service.Antecedentes.AntecedentesService;
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

public class AntecedentesController {

    private final AntecedentesService antecedentesService;

    @PostMapping
    public ResponseEntity<AntecedentesResponseDTO> crear(@Valid @RequestBody AntecedentesCreateDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(antecedentesService.crear(dto));
    }

    @GetMapping
    public ResponseEntity<List<AntecedentesResponseDTO>> listarTodos() {
        return ResponseEntity.ok(antecedentesService.listarTodos());
    }

    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<List<AntecedentesResponseDTO>> listarPorPaciente(@PathVariable Integer pacienteId) {
        return ResponseEntity.ok(antecedentesService.listarPorPaciente(pacienteId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AntecedentesResponseDTO> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(antecedentesService.obtenerPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AntecedentesResponseDTO> actualizar(@PathVariable Integer id,
            @Valid @RequestBody AntecedentesUpdateDTO dto) {
        return ResponseEntity.ok(antecedentesService.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        antecedentesService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
