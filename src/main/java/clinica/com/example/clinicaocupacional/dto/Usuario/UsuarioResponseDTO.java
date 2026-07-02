package clinica.com.example.clinicaocupacional.dto.Usuario;

import clinica.com.example.clinicaocupacional.dto.Documento.DocumentoResponseDTO;
import clinica.com.example.clinicaocupacional.dto.Rol.RolResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioResponseDTO {

    private Integer id;

    private String nombres;

    private String apellidoPaterno;

    private String apellidoMaterno;

    private String correoCoorporativo;

    private DocumentoResponseDTO documento;

    private String numeroDocumento;

    private String telefono;

    private RolResponseDTO rol;
}