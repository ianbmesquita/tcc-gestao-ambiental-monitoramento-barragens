package br.pucmg.sigam.monitoramento.api.controllers;

import br.pucmg.sigam.monitoramento.api.dtos.HabitanteRequestDTO;
import br.pucmg.sigam.monitoramento.api.dtos.HabitanteResponseDTO;
import br.pucmg.sigam.monitoramento.application.domain.habitante.services.HabitanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/habitantes")
public class HabitanteController {
    @Autowired
    private HabitanteService service;

    @GetMapping
    public ResponseEntity<List<HabitanteResponseDTO>> getAllHabitantes() {
        return ResponseEntity.ok().body(service.getAllHabitantes());
    }

    @PostMapping
    public ResponseEntity<HabitanteResponseDTO> saveNewBarragem(@RequestBody HabitanteRequestDTO requestDTO) {
        return new ResponseEntity(service.saveNewHabitante(requestDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HabitanteResponseDTO> editBarragemById(@PathVariable Long id,
                                                                @RequestBody HabitanteRequestDTO requestDTO)
            throws Exception {
        return ResponseEntity.ok().body(service.editHabitante(id, requestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteBarragemById(@PathVariable Long id) throws Exception {
        service.deleteHabitanteById(id);

        return ResponseEntity.noContent().build();
    }
}
