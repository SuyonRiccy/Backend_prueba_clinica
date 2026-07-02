package clinica.com.example.clinicaocupacional.dto.Especialidad;

import java.util.List;

import clinica.com.example.clinicaocupacional.dto.DetalleConsulta.DetalleConsultaResponseDTO;
import clinica.com.example.clinicaocupacional.dto.Personal.PersonalResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EspecialidadResponseDTO {

    private Integer id;

    private String nombre;

    private Boolean vigente;

    private List<PersonalResponseDTO> personales;

    private List<DetalleConsultaResponseDTO> detalleConsultas;
}
