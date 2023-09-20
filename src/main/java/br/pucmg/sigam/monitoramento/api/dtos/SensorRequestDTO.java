package br.pucmg.sigam.monitoramento.api.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SensorRequestDTO {
    private String nome;
    private String fabricante;
    private String tipo;
    private Long idBarragem;
}
