package clinica.com.example.clinicaocupacional.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "personal")
public class Personal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_especialidad")
    private Especialidad especialidad;

    @Column(name = "numero_colegiatura", length = 6, nullable = false, unique = true)
    private String numero_colegiatura;

    @Column(name = "numero_especialidad", length = 6, nullable = true)
    private String numero_especialidad;

    @Column(name = "firma_digital", nullable = false, unique = true)
    private String firma_digital;

    @Column(name = "inicio_contrato", nullable = false)
    private LocalDate inicio_contrato;

    @Column(name = "fin_contrato", nullable = false)
    private LocalDate fin_contrato;

    @Column(nullable = false)
    private boolean vigente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_contrato", nullable = false)
    private Contrato contrato;

}
