package clinica.com.example.clinicaocupacional.dto.Antecedentes;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AntecedentesCreateDTO {

    @NotNull
    private Integer pacienteId;

    private String descripcion;

    @NotNull
    private LocalDate fechaRegistro;
}
