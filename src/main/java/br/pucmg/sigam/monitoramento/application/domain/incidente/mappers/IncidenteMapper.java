package br.pucmg.sigam.monitoramento.application.domain.incidente.mappers;

import br.pucmg.sigam.monitoramento.api.dtos.IncidenteRequestDTO;
import br.pucmg.sigam.monitoramento.api.dtos.IncidenteResponseDTO;
import br.pucmg.sigam.monitoramento.application.domain.incidente.models.Incidente;
import br.pucmg.sigam.monitoramento.application.domain.incidente.models.TipoIncidente;
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
public class IncidenteMapper {
    @Autowired
    private BarragemMapper barragemMapper;

    public Incidente convertIncidenteRequestDTOToIncidenteEntity(final IncidenteRequestDTO requestDTO, final Barragem barragem) {
        return Incidente.builder()
                .dataHora(requestDTO.getDataHora())
                .grauRisco(ClassificacaoRisco.valueOf(requestDTO.getGrauRisco()))
                .tipo(TipoIncidente.valueOf(requestDTO.getAlerta()))
                .barragem(barragem)
                .build();
    }

    public IncidenteResponseDTO convertIncidenteEntityToIncidenteResponseDTO(final Incidente incidente) {
        return IncidenteResponseDTO.builder()
                .id(incidente.getId())
                .dataHora(incidente.getDataHora())
                .grauRisco(incidente.getGrauRisco().getRisco())
                .tipo(incidente.getTipo().getTipo())
                .barragem(barragemMapper.convertBarragemEntityToBarragemResponseDTO(incidente.getBarragem()))
                .build();
    }

    public List<IncidenteResponseDTO> convertListIncidenteEntityToListIncidenteResponseDTO(
            final List<Incidente> incidentes) {
        List<IncidenteResponseDTO> alertasResponseDTO = new ArrayList<>();

        for (Incidente incidente : incidentes) {
            alertasResponseDTO.add(convertIncidenteEntityToIncidenteResponseDTO(incidente));
        }

        return alertasResponseDTO;
    }

    public String convertIncidenteToJsonString(final Incidente incidente) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(incidente);
    }
}
