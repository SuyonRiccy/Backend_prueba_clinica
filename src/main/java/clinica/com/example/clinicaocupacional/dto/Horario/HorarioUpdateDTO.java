package clinica.com.example.clinicaocupacional.dto.Horario;

import java.sql.Time;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HorarioUpdateDTO {

    private Time horaInicio;

    private Time horarioFin;

    private Integer turnoId;

    private Integer contratoId;
}