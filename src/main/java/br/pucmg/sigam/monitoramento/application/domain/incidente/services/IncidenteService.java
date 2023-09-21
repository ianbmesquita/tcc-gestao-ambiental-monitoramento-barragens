package br.pucmg.sigam.monitoramento.application.domain.incidente.services;

import br.pucmg.sigam.monitoramento.api.dtos.IncidenteRequestDTO;
import br.pucmg.sigam.monitoramento.api.dtos.IncidenteResponseDTO;
import br.pucmg.sigam.monitoramento.application.domain.incidente.mappers.IncidenteMapper;
import br.pucmg.sigam.monitoramento.infra.dataproviders.repositories.IncidenteRepository;
import br.pucmg.sigam.monitoramento.infra.dataproviders.repositories.BarragemRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.pucmg.sigam.monitoramento.utils.Messages.BARRAGEM_NAO_ENCONTRADA;
import static br.pucmg.sigam.monitoramento.utils.Messages.SENSOR_NAO_ENCONTRADO;

@Service
public class IncidenteService {
    @Autowired
    private IncidenteRepository incidenteRepository;

    @Autowired
    private BarragemRepository barragemRepository;

    @Autowired
    private IncidenteMapper mapper;

    @Autowired
    private SQSService sqsService;

    public List<IncidenteResponseDTO> getAllIncidentes() {
        return mapper.convertListIncidenteEntityToListIncidenteResponseDTO(incidenteRepository.findAll());
    }

    public IncidenteResponseDTO saveNewIncidente(final IncidenteRequestDTO requestDTO) {
        var barragem = barragemRepository.findById(requestDTO.getIdBarragem())
                .orElseThrow(() -> new EntityNotFoundException(String.format(BARRAGEM_NAO_ENCONTRADA, requestDTO.getIdBarragem())));

        var incidente = incidenteRepository.save(mapper.convertIncidenteRequestDTOToIncidenteEntity(requestDTO, barragem));

        sqsService.sendMessageToQueue(incidente);

        return mapper.convertIncidenteEntityToIncidenteResponseDTO(incidente);
    }
}
