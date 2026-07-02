package clinica.com.example.clinicaocupacional.dto.DetalleConsulta;

import clinica.com.example.clinicaocupacional.dto.Consulta.ConsultaResponseDTO;
import clinica.com.example.clinicaocupacional.dto.Estado.EstadoResponseDTO;
import clinica.com.example.clinicaocupacional.dto.Personal.PersonalResponseDTO;
import clinica.com.example.clinicaocupacional.dto.Especialidad.EspecialidadResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DetalleConsultaResponseDTO {

    private Integer id;

    private ConsultaResponseDTO consulta;

    private PersonalResponseDTO medico;

    private EstadoResponseDTO estado;

    private String resultados;

    private EspecialidadResponseDTO especialidad;
}