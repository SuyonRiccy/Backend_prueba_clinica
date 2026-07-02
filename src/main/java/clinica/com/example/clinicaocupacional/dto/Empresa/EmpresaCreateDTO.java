package clinica.com.example.clinicaocupacional.dto.Empresa;

import jakarta.validation.constraints.NotBlank;
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
public class EmpresaCreateDTO {

    @NotBlank
    @Size(max = 100)
    private String nombre;

    @NotBlank
    @Size(max = 50)
    private String razonSocial;

    @NotNull
    private Integer documentoId;

    @NotBlank
    @Size(max = 11)
    private String numeroDocumento;

    @NotNull
    private Integer sectorId;

    @NotNull
    private Integer usuarioCargoId;

    @NotNull
    private Boolean vigente;
}