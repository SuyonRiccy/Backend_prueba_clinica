package clinica.com.example.clinicaocupacional.model;

import java.sql.Time;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "horario")
public class Horario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "hora_inicio", nullable = false)
    private Time hora_inicio;

    @Column(name = "hora_fin", nullable = false)
    private Time horario_fin;

    @ManyToOne
    @JoinColumn(name = "id_turno")
    private Turno turno;

    @ManyToOne
    @JoinColumn(name = "id_contrato")
    private Contrato contrato;

    
}
