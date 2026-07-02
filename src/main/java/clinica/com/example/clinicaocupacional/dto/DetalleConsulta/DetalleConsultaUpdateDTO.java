package clinica.com.example.clinicaocupacional.dto.DetalleConsulta;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DetalleConsultaUpdateDTO {

    private Integer consultaId;

    private Integer medicoId;

    private Integer estadoId;

    @Size(max = 500)
    private String resultados;

    private Integer especialidadId;
}