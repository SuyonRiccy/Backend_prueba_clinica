package clinica.com.example.clinicaocupacional.controller;

import clinica.com.example.clinicaocupacional.dto.Empresa.EmpresaCreateDTO;
import clinica.com.example.clinicaocupacional.dto.Empresa.EmpresaResponseDTO;
import clinica.com.example.clinicaocupacional.dto.Empresa.EmpresaUpdateDTO;
import clinica.com.example.clinicaocupacional.service.Empresa.EmpresaService;
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
@RequestMapping("/api/empresas")
@RequiredArgsConstructor
public class EmpresaController {

    private final EmpresaService empresaService;

    @PostMapping
    public ResponseEntity<EmpresaResponseDTO> crear(@Valid @RequestBody EmpresaCreateDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(empresaService.crear(dto));
    }

    @GetMapping
    public ResponseEntity<List<EmpresaResponseDTO>> listarTodos() {
        return ResponseEntity.ok(empresaService.listarTodos());
    }

    @GetMapping("/activos")
    public ResponseEntity<List<EmpresaResponseDTO>> listarActivos() {
        return ResponseEntity.ok(empresaService.listarActivos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpresaResponseDTO> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(empresaService.obtenerPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmpresaResponseDTO> actualizar(@PathVariable Integer id, @Valid @RequestBody EmpresaUpdateDTO dto) {
        return ResponseEntity.ok(empresaService.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        empresaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
