package clinica.com.example.clinicaocupacional.dto.DetalleConsulta;

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
public class DetalleConsultaCreateDTO {

    @NotNull
    private Integer consultaId;

    private Integer medicoId;

    @NotNull
    private Integer estadoId;

    @Size(max = 500)
    private String resultados;

    @NotNull
    private Integer especialidadId;
}