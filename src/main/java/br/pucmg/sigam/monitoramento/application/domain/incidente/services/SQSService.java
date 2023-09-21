package br.pucmg.sigam.monitoramento.application.domain.incidente.services;

import br.pucmg.sigam.monitoramento.application.domain.incidente.mappers.IncidenteMapper;
import br.pucmg.sigam.monitoramento.application.domain.incidente.models.Incidente;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SQSService {
    @Autowired
    private AmazonSQSAsync amazonSQSAsync;

    @Autowired
    private IncidenteMapper mapper;

    @Value("${cloud.aws.sqs.queue.url}")
    private String sqsUrl;

    public void sendMessageToQueue(final Incidente incidente) {
        try {
            String message = mapper.convertIncidenteToJsonString(incidente);

            amazonSQSAsync.sendMessage(this.sqsUrl, message);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Ocorreu erro ao enviar a mensagem para a fila: " + e.getMessage());
        }
    }
}
