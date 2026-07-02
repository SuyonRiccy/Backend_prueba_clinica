package clinica.com.example.clinicaocupacional.controller;

import clinica.com.example.clinicaocupacional.dto.Sector.SectorCreateDTO;
import clinica.com.example.clinicaocupacional.dto.Sector.SectorResponseDTO;
import clinica.com.example.clinicaocupacional.dto.Sector.SectorUpdateDTO;
import clinica.com.example.clinicaocupacional.service.Sector.SectorService;
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
@RequestMapping("/api/sectores")
@RequiredArgsConstructor
public class SectorController {

    private final SectorService sectorService;

    @PostMapping
    public ResponseEntity<SectorResponseDTO> crear(@Valid @RequestBody SectorCreateDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(sectorService.crear(dto));
    }

    @GetMapping
    public ResponseEntity<List<SectorResponseDTO>> listarTodos() {
        return ResponseEntity.ok(sectorService.listarTodos());
    }

    @GetMapping("/activos")
    public ResponseEntity<List<SectorResponseDTO>> listarActivos() {
        return ResponseEntity.ok(sectorService.listarActivos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SectorResponseDTO> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(sectorService.obtenerPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SectorResponseDTO> actualizar(@PathVariable Integer id, @Valid @RequestBody SectorUpdateDTO dto) {
        return ResponseEntity.ok(sectorService.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        sectorService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
