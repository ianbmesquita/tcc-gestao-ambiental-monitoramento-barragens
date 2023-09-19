package br.pucmg.sigam.monitoramento.application.domain.barragem.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "barragens")
@Entity(name = "barragens")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Barragem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nome;

    @Column
    private TipoBarragem tipo;

    @Column
    private String municipio;

    @Column
    private String estado;

    @Column
    private ClassificacaoRisco risco;

    @Column
    private StatusBarragem status;
}
