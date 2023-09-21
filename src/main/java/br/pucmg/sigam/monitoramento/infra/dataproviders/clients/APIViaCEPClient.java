package br.pucmg.sigam.monitoramento.infra.dataproviders.clients;

import br.pucmg.sigam.monitoramento.application.domain.localidade.models.Localidade;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@AllArgsConstructor
public class APIViaCEPClient {

    private final RestTemplate restTemplate;
    private final String VIACEP_URL = "https://viacep.com.br/ws/";

    public Localidade getLocalidadeByCEP(final String cep) {
        var url = VIACEP_URL + cep + "/json";

        return restTemplate.getForObject(url, Localidade.class);
    }
}
