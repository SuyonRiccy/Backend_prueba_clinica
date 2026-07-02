package clinica.com.example.clinicaocupacional.dto.Consulta;

import java.time.LocalDate;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConsultaUpdateDTO {

    private Integer pacienteId;

    private LocalDate fechaConsulta;

    private Integer estadoId;

    private Integer turnoId;

    private List<Integer> especialidadesIds;
}