package clinica.com.example.clinicaocupacional.controller;

import clinica.com.example.clinicaocupacional.dto.Personal.PersonalCreateDTO;
import clinica.com.example.clinicaocupacional.dto.Personal.PersonalResponseDTO;
import clinica.com.example.clinicaocupacional.dto.Personal.PersonalUpdateDTO;
import clinica.com.example.clinicaocupacional.service.Personal.PersonalService;
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
@RequestMapping("/api/personal")
@RequiredArgsConstructor
public class PersonalController {

    private final PersonalService personalService;

    @PostMapping
    public ResponseEntity<PersonalResponseDTO> crear(@Valid @RequestBody PersonalCreateDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(personalService.crear(dto));
    }

    @GetMapping
    public ResponseEntity<List<PersonalResponseDTO>> listarTodos() {
        return ResponseEntity.ok(personalService.listarTodos());
    }

    @GetMapping("/activos")
    public ResponseEntity<List<PersonalResponseDTO>> listarActivos() {
        return ResponseEntity.ok(personalService.listarActivos());
    }

    @GetMapping("/especialidad/{especialidadId}")
    public ResponseEntity<List<PersonalResponseDTO>> listarPorEspecialidad(@PathVariable Integer especialidadId) {
        return ResponseEntity.ok(personalService.listarPorEspecialidad(especialidadId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonalResponseDTO> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(personalService.obtenerPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonalResponseDTO> actualizar(@PathVariable Integer id, @Valid @RequestBody PersonalUpdateDTO dto) {
        return ResponseEntity.ok(personalService.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        personalService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
