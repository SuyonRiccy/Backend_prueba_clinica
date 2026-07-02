package clinica.com.example.clinicaocupacional.security.dto;

import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class LoginResponseDTO {
    private String token;
    private String correoCoorporativo;
    private String rol;
    private String nombreCompleto;
}