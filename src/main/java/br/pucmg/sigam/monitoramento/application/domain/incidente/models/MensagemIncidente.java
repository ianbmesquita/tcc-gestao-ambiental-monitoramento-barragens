package br.pucmg.sigam.monitoramento.application.domain.incidente.models;

import br.pucmg.sigam.monitoramento.application.domain.barragem.models.ClassificacaoRisco;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MensagemIncidente implements Serializable {
    private LocalDateTime dataHora;
    private ClassificacaoRisco grauRisco;
    private TipoIncidente tipoIncidente;
    private TipoOrigem origem;
    private String observacoes;
}
