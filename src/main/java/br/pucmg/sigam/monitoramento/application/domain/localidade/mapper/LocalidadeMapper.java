package br.pucmg.sigam.monitoramento.application.domain.localidade.mapper;

import br.pucmg.sigam.monitoramento.api.dtos.LocalidadeResponseDTO;
import br.pucmg.sigam.monitoramento.application.domain.localidade.models.Localidade;
import org.springframework.stereotype.Component;

@Component
public class LocalidadeMapper {
    public LocalidadeResponseDTO convertLocalidadeEntityToLocalidadeResponseDTO(final Localidade localidade) {
        return LocalidadeResponseDTO.builder()
                .cep(localidade.getCep())
                .logradouro(localidade.getLogradouro())
                .complemento(localidade.getComplemento())
                .bairro(localidade.getBairro())
                .cidade(localidade.getLocalidade())
                .uf(localidade.getUf())
                .build();
    }
}
