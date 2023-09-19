package br.pucmg.sigam.monitoramento.application.domain.alerta.mappers;

import br.pucmg.sigam.monitoramento.api.dtos.AlertaRequestDTO;
import br.pucmg.sigam.monitoramento.api.dtos.AlertaResponseDTO;
import br.pucmg.sigam.monitoramento.application.domain.alerta.models.Alerta;
import br.pucmg.sigam.monitoramento.application.domain.alerta.models.TipoAlerta;
import br.pucmg.sigam.monitoramento.application.domain.barragem.mappers.BarragemMapper;
import br.pucmg.sigam.monitoramento.application.domain.barragem.models.Barragem;
import br.pucmg.sigam.monitoramento.application.domain.barragem.models.ClassificacaoRisco;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AlertaMapper {
    @Autowired
    private BarragemMapper barragemMapper;

    public Alerta convertAlertaRequestDTOToAlertaEntity(final AlertaRequestDTO requestDTO, final Barragem barragem) {
        return Alerta.builder()
                .dataHora(requestDTO.getDataHora())
                .grauRisco(ClassificacaoRisco.valueOf(requestDTO.getGrauRisco()))
                .tipo(TipoAlerta.valueOf(requestDTO.getAlerta()))
                .barragem(barragem)
                .build();
    }

    public AlertaResponseDTO convertAlertaEntityToAlertaResponseDTO(final Alerta alerta) {
        return AlertaResponseDTO.builder()
                .id(alerta.getId())
                .dataHora(alerta.getDataHora())
                .grauRisco(alerta.getGrauRisco().getRisco())
                .tipo(alerta.getTipo().getTipo())
                .barragem(barragemMapper.convertBarragemEntityToBarragemResponseDTO(alerta.getBarragem()))
                .build();
    }

    public List<AlertaResponseDTO> convertListAlertaEntityToListAlertaResponseDTO(final List<Alerta> alertas) {
        List<AlertaResponseDTO> alertasResponseDTO = new ArrayList<>();

        for (Alerta alerta : alertas) {
            alertasResponseDTO.add(convertAlertaEntityToAlertaResponseDTO(alerta));
        }

        return alertasResponseDTO;
    }

    public String convertAlertToJsonString(final Alerta alerta) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(alerta);
    }
}
