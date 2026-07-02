package clinica.com.example.clinicaocupacional.dto.Rol;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RolUpdateDTO {

    @Size(max = 20)
    private String nombre;

    private Boolean vigente;
}