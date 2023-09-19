package br.pucmg.sigam.monitoramento.application.domain.alerta.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoAlerta {
    EVACUACAO_IMEDIATA("Evacuação imediata"),
    PERIGO_EMINENTE("Perigo eminente");

    private String tipo;
}
