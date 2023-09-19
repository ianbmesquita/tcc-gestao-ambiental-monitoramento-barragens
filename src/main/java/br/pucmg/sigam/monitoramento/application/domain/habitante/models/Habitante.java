package br.pucmg.sigam.monitoramento.application.domain.habitante.models;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Table(name = "habitantes")
@Entity(name = "habitantes")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Habitante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column
    private String nome;

    @Nullable
    @Column
    private LocalDate nascimento;

    @NotNull
    @Column
    private String telefone;

    @NotNull
    @Column
    private String email;

    @OneToOne(mappedBy = "habitante", cascade = CascadeType.ALL)
    private Endereco endereco;
}