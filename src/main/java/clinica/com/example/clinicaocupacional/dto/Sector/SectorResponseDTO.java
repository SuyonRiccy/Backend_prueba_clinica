package clinica.com.example.clinicaocupacional.dto.Sector;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SectorResponseDTO {

    private Integer id;

    private String nombre;

    private Boolean vigente;
}