package clinica.com.example.clinicaocupacional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Map;

@Component
public class DatabaseCleanUp implements CommandLineRunner {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... args) throws Exception {
        try {
            System.out.println("=== DIAGNÓSTICO DE ASIGNACIÓN DE MÉDICOS ===");
            
            System.out.println("\n--- Turnos ---");
            List<Map<String, Object>> turnos = jdbcTemplate.queryForList("SELECT id, nombre, vigente FROM turno");
            turnos.forEach(t -> System.out.println("Turno: ID=" + t.get("id") + ", Nombre=" + t.get("nombre") + ", Vigente=" + t.get("vigente")));

            System.out.println("\n--- Especialidades ---");
            List<Map<String, Object>> esps = jdbcTemplate.queryForList("SELECT id, nombre, vigente FROM especialidad");
            esps.forEach(e -> System.out.println("Especialidad: ID=" + e.get("id") + ", Nombre=" + e.get("nombre") + ", Vigente=" + e.get("vigente")));

            System.out.println("\n--- Personal/Médicos ---");
            List<Map<String, Object>> pers = jdbcTemplate.queryForList("SELECT id, id_usuario, id_especialidad, vigente FROM personal");
            pers.forEach(p -> System.out.println("Personal: ID=" + p.get("id") + ", UsuarioID=" + p.get("id_usuario") + ", EspecialidadID=" + p.get("id_especialidad") + ", Vigente=" + p.get("vigente")));

            System.out.println("\n--- Horarios ---");
            List<Map<String, Object>> hors = jdbcTemplate.queryForList("SELECT id, id_turno FROM horario");
            hors.forEach(h -> System.out.println("Horario: ID=" + h.get("id") + ", TurnoID=" + h.get("id_turno")));

            System.out.println("\n--- HorarioMes ---");
            List<Map<String, Object>> hms = jdbcTemplate.queryForList("SELECT id, id_horario, id_personal FROM horario_mes");
            hms.forEach(hm -> System.out.println("HorarioMes: ID=" + hm.get("id") + ", HorarioID=" + hm.get("id_horario") + ", PersonalID=" + hm.get("id_personal")));

            System.out.println("=============================================");
            
        } catch (Exception e) {
            System.err.println("Error durante el diagnóstico: " + e.getMessage());
        }
    }
}
