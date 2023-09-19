package br.pucmg.sigam.monitoramento.application.domain.alerta.services;

import br.pucmg.sigam.monitoramento.application.domain.alerta.mappers.AlertaMapper;
import br.pucmg.sigam.monitoramento.application.domain.alerta.models.Alerta;
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
    private AlertaMapper mapper;

    @Value("${cloud.aws.sqs.queue.url}")
    private String sqlUrl;

    public void sendMessageToQueue(final Alerta alerta) {
        try {
            String message = mapper.convertAlertToJsonString(alerta);

            amazonSQSAsync.sendMessage(this.sqlUrl, message);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Ocorreu erro ao enviar a mensagem para a fila: " + e.getMessage());
        }
    }
}
