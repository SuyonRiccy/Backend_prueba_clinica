package clinica.com.example.clinicaocupacional.dto.Personal;

import java.time.LocalDate;

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
public class PersonalCreateDTO {

    private Integer usuarioId;

    private Integer especialidadId;

    @NotBlank
    @Size(max = 6)
    private String numeroColegiatura;

    @Size(max = 6)
    private String numeroEspecialidad;

    @NotBlank
    private String firmaDigital;

    @NotNull
    private LocalDate inicioContrato;

    @NotNull
    private LocalDate finContrato;

    @NotNull
    private Boolean vigente;
    
    @NotNull
    private Integer contratoId;
}