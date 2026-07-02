package clinica.com.example.clinicaocupacional.service.Personal;

import clinica.com.example.clinicaocupacional.dto.Personal.PersonalCreateDTO;
import clinica.com.example.clinicaocupacional.dto.Personal.PersonalResponseDTO;
import clinica.com.example.clinicaocupacional.dto.Personal.PersonalUpdateDTO;
import java.util.List;

public interface PersonalService {
    PersonalResponseDTO crear(PersonalCreateDTO dto);
    PersonalResponseDTO actualizar(Integer id, PersonalUpdateDTO dto);
    PersonalResponseDTO obtenerPorId(Integer id);
    List<PersonalResponseDTO> listarTodos();
    List<PersonalResponseDTO> listarActivos();
    List<PersonalResponseDTO> listarPorEspecialidad(Integer especialidadId);
    void eliminar(Integer id);
}
