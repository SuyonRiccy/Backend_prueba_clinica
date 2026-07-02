package clinica.com.example.clinicaocupacional.service.Contrato;

import clinica.com.example.clinicaocupacional.dto.Contrato.ContratoCreateDTO;
import clinica.com.example.clinicaocupacional.dto.Contrato.ContratoResponseDTO;
import clinica.com.example.clinicaocupacional.dto.Contrato.ContratoUpdateDTO;
import clinica.com.example.clinicaocupacional.exception.ResourceNotFoundException;
import clinica.com.example.clinicaocupacional.model.Contrato;
import clinica.com.example.clinicaocupacional.repository.ContratoRepository;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContratoServiceImpl implements ContratoService {

    private final ContratoRepository contratoRepository;

    @Override
    public ContratoResponseDTO crear(ContratoCreateDTO dto) {
        Contrato contrato = mapToEntity(dto);
        contrato.setVigente(true);
        return mapToResponse(contratoRepository.save(contrato));
    }

    @Override
    public ContratoResponseDTO actualizar(Integer id, ContratoUpdateDTO dto) {
        Contrato contrato = contratoRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Contrato", "id", id));
        if (dto.getNombre() != null) {
            contrato.setNombre(dto.getNombre());
        }
        if (dto.getVigente() != null) {
            contrato.setVigente(dto.getVigente());
        }
        return mapToResponse(contratoRepository.save(contrato));
    }

    @Override
    public ContratoResponseDTO obtenerPorId(Integer id) {
        return contratoRepository.findById(id)
            .map(this::mapToResponse)
            .orElseThrow(() -> new ResourceNotFoundException("Contrato", "id", id));
    }

    @Override
    public List<ContratoResponseDTO> listarTodos() {
        return StreamSupport.stream(contratoRepository.findAll().spliterator(), false)
            .map(this::mapToResponse)
            .collect(Collectors.toList());
    }

    @Override
    public List<ContratoResponseDTO> listarActivos() {
        return StreamSupport.stream(contratoRepository.findAll().spliterator(), false)
            .filter(Contrato::isVigente)
            .map(this::mapToResponse)
            .collect(Collectors.toList());
    }

    @Override
    public void eliminar(Integer id) {
        Contrato contrato = contratoRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Contrato", "id", id));
        contrato.setVigente(false);
        contratoRepository.save(contrato);
    }

    private Contrato mapToEntity(ContratoCreateDTO dto) {
        Contrato contrato = new Contrato();
        contrato.setNombre(dto.getNombre());
        return contrato;
    }

    private ContratoResponseDTO mapToResponse(Contrato contrato) {
        ContratoResponseDTO response = new ContratoResponseDTO();
        response.setId(contrato.getId());
        response.setNombre(contrato.getNombre());
        response.setVigente(contrato.isVigente());
        return response;
    }
}
