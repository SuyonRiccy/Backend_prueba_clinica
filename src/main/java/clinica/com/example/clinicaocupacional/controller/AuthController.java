package clinica.com.example.clinicaocupacional.controller;

import clinica.com.example.clinicaocupacional.model.Usuario;
import clinica.com.example.clinicaocupacional.repository.UsuarioRepository;
import clinica.com.example.clinicaocupacional.security.JwtUtil;
import clinica.com.example.clinicaocupacional.security.dto.LoginRequestDTO;
import clinica.com.example.clinicaocupacional.security.dto.LoginResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UsuarioRepository usuarioRepository;
    private final JwtUtil jwtUtil;

    @PostMapping("/login")
public ResponseEntity<?> login(@Valid @RequestBody LoginRequestDTO dto) {
    
    // LOG TEMPORAL
    System.out.println("=== LOGIN ATTEMPT ===");
    System.out.println("Correo recibido: " + dto.getCorreoCoorporativo());
    
    try {
        System.out.println("Intentando autenticar...");
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                dto.getCorreoCoorporativo(),
                dto.getContrasena()
            )
        );
        System.out.println("Autenticacion exitosa");

        Usuario usuario = usuarioRepository
            .findByCorreoCoorporativo(dto.getCorreoCoorporativo())
            .orElseThrow();

        System.out.println("Usuario encontrado: " + usuario.getNombres());

        String token = jwtUtil.generateToken(
            usuario.getCorreoCoorporativo(),
            usuario.getRol().getNombre()
        );

        LoginResponseDTO response = new LoginResponseDTO(
            token,
            usuario.getCorreoCoorporativo(),
            usuario.getRol().getNombre(),
            usuario.getNombres() + " " + usuario.getApellidoPaterno()
        );

        return ResponseEntity.ok(response);

    } catch (AuthenticationException e) {
        System.out.println("=== AUTH ERROR: " + e.getMessage());
        System.out.println("=== CAUSA: " + e.getCause());
        return ResponseEntity.status(401).body("Credenciales incorrectas: " + e.getMessage());
    } catch (Exception e) {
        System.out.println("=== ERROR GENERAL: " + e.getMessage());
        e.printStackTrace();
        return ResponseEntity.status(500).body("Error: " + e.getMessage());
    }
}
}
