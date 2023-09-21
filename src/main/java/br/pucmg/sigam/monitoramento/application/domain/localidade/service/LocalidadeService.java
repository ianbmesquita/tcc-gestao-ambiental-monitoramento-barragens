package br.pucmg.sigam.monitoramento.application.domain.localidade.service;

import br.pucmg.sigam.monitoramento.api.dtos.LocalidadeResponseDTO;
import br.pucmg.sigam.monitoramento.application.domain.localidade.mapper.LocalidadeMapper;
import br.pucmg.sigam.monitoramento.infra.dataproviders.clients.APIViaCEPClient;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocalidadeService {
    @Autowired
    private LocalidadeMapper mapper;

    @Autowired
    private APIViaCEPClient client;

    public LocalidadeResponseDTO consultarLocalidadeViaAPI(final String cep) {
        return mapper.convertLocalidadeEntityToLocalidadeResponseDTO(client.getLocalidadeByCEP(cep));
    }
}
