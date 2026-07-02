package clinica.com.example.clinicaocupacional.dto.Usuario;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioUpdateDTO {

    @Size(max = 50)
    private String nombres;

    @Size(max = 20)
    private String apellidoPaterno;

    @Size(max = 20)
    private String apellidoMaterno;

    @Size(max = 100)
    private String correoCoorporativo;

    private String contrasena;

    private Integer documentoId;

    @Size(max = 25)
    private String numeroDocumento;

    @Size(max = 12)
    private String telefono;

    private Integer rolId;
}