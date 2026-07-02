package clinica.com.example.clinicaocupacional.dto.Contrato;

import java.util.List;

import clinica.com.example.clinicaocupacional.dto.Horario.HorarioResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContratoResponseDTO {

    private Integer id;

    private String nombre;

    private Boolean vigente;

    private List<HorarioResponseDTO> horarios;
}