package clinica.com.example.clinicaocupacional.dto.Usuario;

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
public class UsuarioCreateDTO {

    @NotBlank
    @Size(max = 50)
    private String nombres;

    @NotBlank
    @Size(max = 20)
    private String apellidoPaterno;

    @NotBlank
    @Size(max = 20)
    private String apellidoMaterno;

    @NotBlank
    @Size(max = 100)
    private String correoCoorporativo;

    @NotBlank
    private String contrasena;

    @NotNull
    private Integer documentoId;

    @NotBlank
    @Size(max = 25)
    private String numeroDocumento;

    @NotBlank
    @Size(max = 12)
    private String telefono;

    @NotNull
    private Integer rolId;
}