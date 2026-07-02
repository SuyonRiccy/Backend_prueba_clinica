package clinica.com.example.clinicaocupacional.dto.Empresa;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmpresaUpdateDTO {

    @Size(max = 100)
    private String nombre;

    @Size(max = 50)
    private String razonSocial;

    private Integer documentoId;

    @Size(max = 11)
    private String numeroDocumento;

    private Integer sectorId;

    private Integer usuarioCargoId;

    private Boolean vigente;
}
