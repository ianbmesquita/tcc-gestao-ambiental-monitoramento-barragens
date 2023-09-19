package br.pucmg.sigam.monitoramento.application.domain.barragem.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoBarragem {
    CONCRETO("concreto"),
    ENROCAMENTO("enrocamento"),
    ATERRO("aterro");

    private String tipo;
}
