package clinica.com.example.clinicaocupacional.dto.Consulta;

import java.time.LocalDate;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConsultaCreateDTO {

    @NotNull
    private Integer pacienteId;

    @NotNull
    private LocalDate fechaConsulta;

    private Integer estadoId;

    @NotNull
    private Integer turnoId;

    @NotEmpty
    private List<Integer> especialidadesIds;
}