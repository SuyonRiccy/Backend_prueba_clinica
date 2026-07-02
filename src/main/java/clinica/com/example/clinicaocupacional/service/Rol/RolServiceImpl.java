package clinica.com.example.clinicaocupacional.service.Rol;

import clinica.com.example.clinicaocupacional.dto.Rol.RolCreateDTO;
import clinica.com.example.clinicaocupacional.dto.Rol.RolResponseDTO;
import clinica.com.example.clinicaocupacional.dto.Rol.RolUpdateDTO;
import clinica.com.example.clinicaocupacional.exception.ResourceNotFoundException;
import clinica.com.example.clinicaocupacional.model.Rol;
import clinica.com.example.clinicaocupacional.repository.RolRepository;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RolServiceImpl implements RolService {

    private final RolRepository rolRepository;

    @Override
    public RolResponseDTO crear(RolCreateDTO dto) {
        Rol rol = mapToEntity(dto);
        rol.setVigente(true);
        return mapToResponse(rolRepository.save(rol));
    }

    @Override
    public RolResponseDTO actualizar(Integer id, RolUpdateDTO dto) {
        Rol rol = rolRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Rol", "id", id));
        if (dto.getNombre() != null) {
            rol.setNombre(dto.getNombre());
        }
        if (dto.getVigente() != null) {
            rol.setVigente(dto.getVigente());
        }
        return mapToResponse(rolRepository.save(rol));
    }

    @Override
    public RolResponseDTO obtenerPorId(Integer id) {
        return rolRepository.findById(id)
            .map(this::mapToResponse)
            .orElseThrow(() -> new ResourceNotFoundException("Rol", "id", id));
    }

    @Override
    public List<RolResponseDTO> listarTodos() {
        return StreamSupport.stream(rolRepository.findAll().spliterator(), false)
            .map(this::mapToResponse)
            .collect(Collectors.toList());
    }

    @Override
    public List<RolResponseDTO> listarActivos() {
        return StreamSupport.stream(rolRepository.findAll().spliterator(), false)
            .filter(Rol::isVigente)
            .map(this::mapToResponse)
            .collect(Collectors.toList());
    }

    @Override
    public void eliminar(Integer id) {
        Rol rol = rolRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Rol", "id", id));
        rol.setVigente(false);
        rolRepository.save(rol);
    }

    private Rol mapToEntity(RolCreateDTO dto) {
        Rol rol = new Rol();
        rol.setNombre(dto.getNombre());
        return rol;
    }

    private RolResponseDTO mapToResponse(Rol rol) {
        RolResponseDTO response = new RolResponseDTO();
        response.setId(rol.getId());
        response.setNombre(rol.getNombre());
        response.setVigente(rol.isVigente());
        return response;
    }
}
