package clinica.com.example.clinicaocupacional.dto.HorarioMes;

import clinica.com.example.clinicaocupacional.dto.Horario.HorarioResponseDTO;
import clinica.com.example.clinicaocupacional.dto.Personal.PersonalResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HorarioMesResponseDTO {

    private Integer id;

    private HorarioResponseDTO horario;

    private PersonalResponseDTO personal;
}