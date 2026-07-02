package clinica.com.example.clinicaocupacional.dto.Antecedentes;

import java.time.LocalDate;

import clinica.com.example.clinicaocupacional.dto.Usuario.UsuarioResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AntecedentesResponseDTO {
    private Integer id;
    private UsuarioResponseDTO paciente;
    private String descripcion;
    private LocalDate fechaRegistro;
}
