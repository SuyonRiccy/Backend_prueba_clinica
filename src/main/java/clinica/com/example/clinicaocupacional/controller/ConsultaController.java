package clinica.com.example.clinicaocupacional.controller;

import clinica.com.example.clinicaocupacional.dto.Consulta.ConsultaCreateDTO;
import clinica.com.example.clinicaocupacional.dto.Consulta.ConsultaResponseDTO;
import clinica.com.example.clinicaocupacional.dto.Consulta.ConsultaUpdateDTO;
import clinica.com.example.clinicaocupacional.service.Consulta.ConsultaService;
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
@RequestMapping("/api/consultas")
@RequiredArgsConstructor
public class ConsultaController {

    private final ConsultaService consultaService;

    @PostMapping
    public ResponseEntity<ConsultaResponseDTO> crear(@Valid @RequestBody ConsultaCreateDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(consultaService.crear(dto));
    }

    @GetMapping
    public ResponseEntity<List<ConsultaResponseDTO>> listarTodos() {
        return ResponseEntity.ok(consultaService.listarTodos());
    }

    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<List<ConsultaResponseDTO>> listarPorPaciente(@PathVariable Integer pacienteId) {
        return ResponseEntity.ok(consultaService.listarPorPaciente(pacienteId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsultaResponseDTO> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(consultaService.obtenerPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConsultaResponseDTO> actualizar(@PathVariable Integer id, @Valid @RequestBody ConsultaUpdateDTO dto) {
        return ResponseEntity.ok(consultaService.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        consultaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}