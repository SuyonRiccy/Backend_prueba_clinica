package clinica.com.example.clinicaocupacional.dto.Especialidad;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EspecialidadUpdateDTO {

    @Size(max = 50)
    private String nombre;

    private Boolean vigente;
}
