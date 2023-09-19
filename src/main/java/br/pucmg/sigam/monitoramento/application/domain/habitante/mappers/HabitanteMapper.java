package br.pucmg.sigam.monitoramento.application.domain.habitante.mappers;

import br.pucmg.sigam.monitoramento.api.dtos.EnderecoRequestDTO;
import br.pucmg.sigam.monitoramento.api.dtos.EnderecoResponseDTO;
import br.pucmg.sigam.monitoramento.api.dtos.HabitanteRequestDTO;
import br.pucmg.sigam.monitoramento.api.dtos.HabitanteResponseDTO;
import br.pucmg.sigam.monitoramento.application.domain.habitante.models.Endereco;
import br.pucmg.sigam.monitoramento.application.domain.habitante.models.Habitante;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class HabitanteMapper {

    public Habitante convertHabitanteRequestDTOToHabitanteEntity(final HabitanteRequestDTO requestDTO) {
        return Habitante.builder()
                .nome(requestDTO.getNome())
                .nascimento(requestDTO.getNascimento())
                .telefone(requestDTO.getTelefone())
                .email(requestDTO.getEmail())
                .endereco(convertEnderecoRequestDTOToEnderecoEntity(requestDTO.getEndereco()))
                .build();
    }

    private Endereco convertEnderecoRequestDTOToEnderecoEntity(final EnderecoRequestDTO requestDTO) {
        return Endereco.builder()
                .cep(requestDTO.getCep())
                .logradouro(requestDTO.getLogradouro())
                .numero(requestDTO.getNumero())
                .complemento(requestDTO.getComplemento())
                .municipio(requestDTO.getMunicipio())
                .estado(requestDTO.getEstado())
                .build();
    }

    public HabitanteResponseDTO convertHabitanteEntityToHabitanteResponseDTO(final Habitante habitante) {
        return HabitanteResponseDTO.builder()
                .id(habitante.getId())
                .nome(habitante.getNome())
                .nascimento(habitante.getNascimento())
                .telefone(habitante.getTelefone())
                .email(habitante.getEmail())
                .endereco(convertEnderecoEntityToEnderecoResponseDTO(habitante.getEndereco()))
                .build();
    }

    private EnderecoResponseDTO convertEnderecoEntityToEnderecoResponseDTO(final Endereco endereco) {
        return EnderecoResponseDTO.builder()
                .id(endereco.getId())
                .cep(endereco.getCep())
                .logradouro(endereco.getLogradouro())
                .numero(endereco.getNumero())
                .complemento(endereco.getComplemento())
                .municipio(endereco.getMunicipio())
                .estado(endereco.getEstado())
                .build();
    }

    public List<HabitanteResponseDTO> convertListHabitanteEntityToListHabitanteResponseDTO(List<Habitante> habitantes) {
        List habitantesDTO = new ArrayList();

        for (Habitante habitante : habitantes) {
            habitantesDTO.add(convertHabitanteEntityToHabitanteResponseDTO(habitante));
        }

        return habitantesDTO;
    }
}
