package clinica.com.example.clinicaocupacional.dto.Antecedentes;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AntecedentesUpdateDTO {

    private Integer pacienteId;

    private String descripcion;

    private LocalDate fechaRegistro;
}
