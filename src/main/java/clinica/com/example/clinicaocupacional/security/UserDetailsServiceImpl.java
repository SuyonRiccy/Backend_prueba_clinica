package clinica.com.example.clinicaocupacional.security;

import clinica.com.example.clinicaocupacional.model.Usuario;
import clinica.com.example.clinicaocupacional.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String correo)
            throws UsernameNotFoundException {
        
        Usuario usuario = usuarioRepository
        .findByCorreoCoorporativo(correo)  // nombre del método actualizado
        .orElseThrow(() -> new UsernameNotFoundException(
            "Usuario no encontrado: " + correo));

        return new org.springframework.security.core.userdetails.User(
            usuario.getCorreoCoorporativo(),
            usuario.getContrasena(),
            List.of(new SimpleGrantedAuthority(
                "ROLE_" + usuario.getRol().getNombre().toUpperCase()))
        );
    }
}