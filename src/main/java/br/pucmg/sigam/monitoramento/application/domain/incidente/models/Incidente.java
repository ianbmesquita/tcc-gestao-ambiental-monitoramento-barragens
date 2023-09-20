package br.pucmg.sigam.monitoramento.application.domain.incidente.models;

import br.pucmg.sigam.monitoramento.application.domain.barragem.models.Barragem;
import br.pucmg.sigam.monitoramento.application.domain.barragem.models.ClassificacaoRisco;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "incidentes")
@Entity(name = "incidentes")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Incidente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dataHora;

    private ClassificacaoRisco grauRisco;

    private TipoIncidente tipo;

    @ManyToOne
    @JoinColumn(name = "id_barragem")
    private Barragem barragem;
}
