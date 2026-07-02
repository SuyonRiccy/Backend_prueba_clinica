package clinica.com.example.clinicaocupacional.dto.Horario;

import java.sql.Time;

import clinica.com.example.clinicaocupacional.dto.Contrato.ContratoResponseDTO;
import clinica.com.example.clinicaocupacional.dto.Turno.TurnoResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HorarioResponseDTO {

    private Integer id;

    private Time horaInicio;

    private Time horarioFin;

    private TurnoResponseDTO turno;

    private ContratoResponseDTO contrato;
}