package clinica.com.example.clinicaocupacional.dto.Personal;

import java.time.LocalDate;
import java.util.List;

import clinica.com.example.clinicaocupacional.dto.Contrato.ContratoResponseDTO;
import clinica.com.example.clinicaocupacional.dto.Especialidad.EspecialidadResponseDTO;
import clinica.com.example.clinicaocupacional.dto.HorarioMes.HorarioMesResponseDTO;
import clinica.com.example.clinicaocupacional.dto.Usuario.UsuarioResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonalResponseDTO {

    private Integer id;

    private UsuarioResponseDTO usuario;

    private EspecialidadResponseDTO especialidad;

    private String numeroColegiatura;

    private String numeroEspecialidad;

    private String firmaDigital;

    private LocalDate inicioContrato;

    private LocalDate finContrato;

    private Boolean vigente;

    private List<HorarioMesResponseDTO> horarioMeses;
    
    private Integer contratoId;
 
    private ContratoResponseDTO contrato;
}
