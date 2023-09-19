package br.pucmg.sigam.monitoramento.api.controllers;

import br.pucmg.sigam.monitoramento.api.dtos.BarragemRequestDTO;
import br.pucmg.sigam.monitoramento.api.dtos.BarragemResponseDTO;
import br.pucmg.sigam.monitoramento.application.domain.barragem.services.BarragemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/barragens")
public class BarragemController {
    @Autowired
    private BarragemService service;

    @GetMapping
    public ResponseEntity<List<BarragemResponseDTO>> getAllBarragens() {
        return ResponseEntity.ok().body(service.getAllBarragens());
    }

    @PostMapping
    public ResponseEntity<BarragemResponseDTO> saveNewBarragem(@RequestBody BarragemRequestDTO requestDTO) {
        return new ResponseEntity(service.saveNewBarragem(requestDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BarragemResponseDTO> editBarragemById(@PathVariable Long id,
                                                                @RequestBody BarragemRequestDTO requestDTO)
            throws Exception {
        return ResponseEntity.ok().body(service.editBarragem(id, requestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteBarragemById(@PathVariable Long id) throws Exception {
        service.deleteBarragemById(id);

        return ResponseEntity.noContent().build();
    }
}
