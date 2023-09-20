package br.pucmg.sigam.monitoramento.api.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IncidenteRequestDTO {
    private Long idBarragem;
    private LocalDateTime dataHora;
    private String grauRisco;
    private String alerta;
}
