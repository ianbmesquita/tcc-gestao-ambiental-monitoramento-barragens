package br.pucmg.sigam.monitoramento.api.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IncidenteResponseDTO {
    private Long id;
    private LocalDateTime dataHora;
    private String grauRisco;
    private String tipo;
    private BarragemResponseDTO barragem;
}
