package br.pucmg.sigam.monitoramento.application.domain.barragem.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ClassificacaoRisco {
    BAIXO("baixo"),
    MEDIO("médio"),
    ALTO("alto");

    private String risco;
}
