package br.pucmg.sigam.monitoramento.api.controllers;

import br.pucmg.sigam.monitoramento.api.dtos.LeituraSensorRequestDTO;
import br.pucmg.sigam.monitoramento.api.dtos.SensorRequestDTO;
import br.pucmg.sigam.monitoramento.api.dtos.SensorResponseDTO;
import br.pucmg.sigam.monitoramento.application.domain.sensor.services.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/sensores")
public class SensorController {
    @Autowired
    private SensorService service;

    @PreAuthorize("hasRole('USER')")
    @GetMapping
    public ResponseEntity<List<SensorResponseDTO>> getAllSensores() {
        return ResponseEntity.ok().body(service.getAllSensores());
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping
    public ResponseEntity<SensorResponseDTO> saveNewSensor(@RequestBody SensorRequestDTO requestDTO) {
        return new ResponseEntity(service.saveNewSensor(requestDTO), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("/{id}")
    public ResponseEntity<SensorResponseDTO> editSensorById(@PathVariable Long id,
                                                                @RequestBody SensorRequestDTO requestDTO)
            throws Exception {
        return ResponseEntity.ok().body(service.editSensor(id, requestDTO));
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/{id}")
    public ResponseEntity deleteSensorById(@PathVariable Long id) throws Exception {
        service.deleteSensorById(id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/leitura")
    public ResponseEntity readSensorData(@RequestBody LeituraSensorRequestDTO requestDTO) {
        service.readSensorData(requestDTO);

        return ResponseEntity.noContent().build();
    }
}
