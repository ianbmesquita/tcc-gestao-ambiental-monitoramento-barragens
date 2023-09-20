package br.pucmg.sigam.monitoramento.application.domain.sensor.services;

import br.pucmg.sigam.monitoramento.api.dtos.LeituraSensorRequestDTO;
import br.pucmg.sigam.monitoramento.api.dtos.SensorRequestDTO;
import br.pucmg.sigam.monitoramento.api.dtos.SensorResponseDTO;
import br.pucmg.sigam.monitoramento.application.domain.barragem.models.ClassificacaoRisco;
import br.pucmg.sigam.monitoramento.application.domain.incidente.services.IncidenteService;
import br.pucmg.sigam.monitoramento.application.domain.sensor.mappers.SensorMapper;
import br.pucmg.sigam.monitoramento.application.domain.sensor.models.TipoSensor;
import br.pucmg.sigam.monitoramento.infra.dataproviders.repositories.BarragemRepository;
import br.pucmg.sigam.monitoramento.infra.dataproviders.repositories.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SensorService {
    @Autowired
    private SensorRepository sensorRepository;

    @Autowired
    private BarragemRepository barragemRepository;

    @Autowired
    private SensorMapper mapper;

    @Autowired
    private IncidenteService incidenteService;

    public List<SensorResponseDTO> getAllSensores() {
        return mapper.convertListSensorEntityToListSensorResponseDTO(sensorRepository.findAll());
    }

    public SensorResponseDTO saveNewSensor(final SensorRequestDTO requestDTO) {
        var barragem = barragemRepository.findById(requestDTO.getIdBarragem())
                .orElseThrow(() -> new RuntimeException("Barragem não encontrada com o ID: " + requestDTO.getIdBarragem()));

        var sensor = sensorRepository.save(mapper.convertSensorRequestDTOToSensorEntity(requestDTO, barragem));

        return mapper.convertSensorEntityToSensorResponseDTO(sensor);
    }

    public SensorResponseDTO editSensor(final Long id, final SensorRequestDTO requestDTO) throws Exception {
        var sensor = sensorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sensor não encontrado com o ID: " + id));

        sensor.setNome(requestDTO.getNome());
        sensor.setFabricante(requestDTO.getFabricante());
        sensor.setTipo(TipoSensor.valueOf(requestDTO.getTipo()));

        var sensorAtualizado = sensorRepository.save(sensor);

        return mapper.convertSensorEntityToSensorResponseDTO(sensorAtualizado);
    }

    public void deleteSensorById(final Long id) throws Exception {
        var sensor = sensorRepository.findById(id).orElseThrow(() -> new Exception());

        sensorRepository.delete(sensor);
    }

    public void readSensorData(final LeituraSensorRequestDTO requestDTO) {
        if (requestDTO.getGrauRisco().equals(ClassificacaoRisco.ALTO.getRisco())) {
            incidenteService.saveNewIncidente(mapper.convertLeituraSensorRequestDTOToIncidenteRequestDTO(requestDTO));
        }
    }
}
