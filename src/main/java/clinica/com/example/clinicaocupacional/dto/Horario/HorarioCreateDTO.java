package clinica.com.example.clinicaocupacional.dto.Horario;

import java.sql.Time;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HorarioCreateDTO {

    @NotNull
    private Time horaInicio;

    @NotNull
    private Time horarioFin;

    private Integer turnoId;

    private Integer contratoId;
}