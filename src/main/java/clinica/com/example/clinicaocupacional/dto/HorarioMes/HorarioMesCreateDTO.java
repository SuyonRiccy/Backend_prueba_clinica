package clinica.com.example.clinicaocupacional.dto.HorarioMes;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HorarioMesCreateDTO {

    @NotNull
    private Integer horarioId;

    private Integer personalId;
}