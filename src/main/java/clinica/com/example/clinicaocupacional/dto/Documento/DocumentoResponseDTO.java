package clinica.com.example.clinicaocupacional.dto.Documento;

import java.util.List;

import clinica.com.example.clinicaocupacional.dto.Empresa.EmpresaResponseDTO;
import clinica.com.example.clinicaocupacional.dto.Usuario.UsuarioResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DocumentoResponseDTO {

    private Integer id;

    private String nombre;

    private Boolean vigente;

    private List<UsuarioResponseDTO> usuarios;

    private List<EmpresaResponseDTO> empresas;
}