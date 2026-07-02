package clinica.com.example.clinicaocupacional.dto.Estado;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EstadoUpdateDTO {

    @Size(max = 30)
    private String nombre;

    private Boolean vigente;
}