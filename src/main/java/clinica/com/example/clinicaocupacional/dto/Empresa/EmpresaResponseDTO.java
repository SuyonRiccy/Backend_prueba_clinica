package clinica.com.example.clinicaocupacional.dto.Empresa;

import clinica.com.example.clinicaocupacional.dto.Documento.DocumentoResponseDTO;
import clinica.com.example.clinicaocupacional.dto.Sector.SectorResponseDTO;
import clinica.com.example.clinicaocupacional.dto.Usuario.UsuarioResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmpresaResponseDTO {

    private Integer id;

    private String nombre;

    private String razonSocial;

    private DocumentoResponseDTO documento;

    private String numeroDocumento;

    private SectorResponseDTO sector;

    private UsuarioResponseDTO usuarioCargo;

    private Boolean vigente;
}