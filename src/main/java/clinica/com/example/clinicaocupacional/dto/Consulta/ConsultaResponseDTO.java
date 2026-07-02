package clinica.com.example.clinicaocupacional.dto.Consulta;

import java.time.LocalDate;
import java.util.List;

import clinica.com.example.clinicaocupacional.dto.DetalleConsulta.DetalleConsultaResponseDTO;
import clinica.com.example.clinicaocupacional.dto.Estado.EstadoResponseDTO;
import clinica.com.example.clinicaocupacional.dto.Usuario.UsuarioResponseDTO;
import clinica.com.example.clinicaocupacional.dto.Turno.TurnoResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConsultaResponseDTO {

    private Integer id;

    private UsuarioResponseDTO paciente;

    private LocalDate fechaConsulta;

    private EstadoResponseDTO estado;

    private TurnoResponseDTO turno;

    private List<DetalleConsultaResponseDTO> detalleConsultas;
}