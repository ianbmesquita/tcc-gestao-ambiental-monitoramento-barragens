package br.pucmg.sigam.monitoramento.application.domain.alerta.services;

import br.pucmg.sigam.monitoramento.api.dtos.AlertaRequestDTO;
import br.pucmg.sigam.monitoramento.api.dtos.AlertaResponseDTO;
import br.pucmg.sigam.monitoramento.application.domain.alerta.mappers.AlertaMapper;
import br.pucmg.sigam.monitoramento.infra.dataproviders.repositories.AlertaRepository;
import br.pucmg.sigam.monitoramento.infra.dataproviders.repositories.BarragemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlertaService {
    @Autowired
    private AlertaRepository alertaRepository;

    @Autowired
    private BarragemRepository barragemRepository;

    @Autowired
    private AlertaMapper mapper;

    @Autowired
    private SQSService sqsService;

    public List<AlertaResponseDTO> getAllAlertas() {
        return mapper.convertListAlertaEntityToListAlertaResponseDTO(alertaRepository.findAll());
    }

    public AlertaResponseDTO saveNewAlerta(final AlertaRequestDTO requestDTO) {
        var barragem = barragemRepository.findById(requestDTO.getIdBarragem())
                .orElseThrow(() -> new RuntimeException("Barragem não encontrada com o ID: " + requestDTO.getIdBarragem()));

        var alerta = alertaRepository.save(mapper.convertAlertaRequestDTOToAlertaEntity(requestDTO, barragem));

        sqsService.sendMessageToQueue(alerta);

        return mapper.convertAlertaEntityToAlertaResponseDTO(alerta);
    }
}
