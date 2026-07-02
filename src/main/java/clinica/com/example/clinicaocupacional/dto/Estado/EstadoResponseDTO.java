package clinica.com.example.clinicaocupacional.dto.Estado;

import java.util.List;

import clinica.com.example.clinicaocupacional.dto.Consulta.ConsultaResponseDTO;
import clinica.com.example.clinicaocupacional.dto.DetalleConsulta.DetalleConsultaResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EstadoResponseDTO {

    private Integer id;

    private String nombre;

    private Boolean vigente;

    private List<ConsultaResponseDTO> consultas;

    private List<DetalleConsultaResponseDTO> detalleConsultas;
}
