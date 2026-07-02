package clinica.com.example.clinicaocupacional.dto.Estado;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EstadoCreateDTO {

    @NotBlank
    @Size(max = 30)
    private String nombre;

    @NotNull
    private Boolean vigente;
}