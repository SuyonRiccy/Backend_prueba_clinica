package clinica.com.example.clinicaocupacional.controller;

import clinica.com.example.clinicaocupacional.dto.DetalleConsulta.DetalleConsultaCreateDTO;
import clinica.com.example.clinicaocupacional.dto.DetalleConsulta.DetalleConsultaResponseDTO;
import clinica.com.example.clinicaocupacional.dto.DetalleConsulta.DetalleConsultaUpdateDTO;
import clinica.com.example.clinicaocupacional.service.DetalleConsulta.DetalleConsultaService;
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
@RequestMapping("/api/detalle-consultas")
@RequiredArgsConstructor
public class DetalleConsultaController {

    private final DetalleConsultaService detalleConsultaService;

    @PostMapping
    public ResponseEntity<DetalleConsultaResponseDTO> crear(@Valid @RequestBody DetalleConsultaCreateDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(detalleConsultaService.crear(dto));
    }

    @GetMapping
    public ResponseEntity<List<DetalleConsultaResponseDTO>> listarTodos() {
        return ResponseEntity.ok(detalleConsultaService.listarTodos());
    }

    @GetMapping("/consulta/{consultaId}")
    public ResponseEntity<List<DetalleConsultaResponseDTO>> listarPorConsulta(@PathVariable Integer consultaId) {
        return ResponseEntity.ok(detalleConsultaService.listarPorConsulta(consultaId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalleConsultaResponseDTO> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(detalleConsultaService.obtenerPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetalleConsultaResponseDTO> actualizar(@PathVariable Integer id, @Valid @RequestBody DetalleConsultaUpdateDTO dto) {
        return ResponseEntity.ok(detalleConsultaService.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        detalleConsultaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
