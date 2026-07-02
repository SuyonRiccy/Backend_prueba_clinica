package clinica.com.example.clinicaocupacional.dto.Personal;

import java.time.LocalDate;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonalUpdateDTO {

    private Integer usuarioId;

    private Integer especialidadId;

    @Size(max = 6)
    private String numeroColegiatura;

    @Size(max = 6)
    private String numeroEspecialidad;

    private String firmaDigital;

    private LocalDate inicioContrato;

    private LocalDate finContrato;

    private Boolean vigente;
}