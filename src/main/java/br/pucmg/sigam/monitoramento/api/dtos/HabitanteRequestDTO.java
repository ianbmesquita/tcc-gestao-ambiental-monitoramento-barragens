package br.pucmg.sigam.monitoramento.api.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HabitanteRequestDTO {
    private String nome;
    private LocalDate nascimento;
    private String telefone;
    private String email;
    private EnderecoRequestDTO endereco;
}
