package br.pucmg.sigam.monitoramento.application.domain.barragem.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusBarragem {
    NORMAL("normal"),
    ATENCAO("atenção"),
    CRITICO("critico");

    private String status;
}
