package br.pucmg.sigam.monitoramento.application.domain.barragem.services;

import br.pucmg.sigam.monitoramento.api.dtos.BarragemRequestDTO;
import br.pucmg.sigam.monitoramento.api.dtos.BarragemResponseDTO;
import br.pucmg.sigam.monitoramento.application.domain.barragem.mappers.BarragemMapper;
import br.pucmg.sigam.monitoramento.application.domain.barragem.models.ClassificacaoRisco;
import br.pucmg.sigam.monitoramento.application.domain.barragem.models.StatusBarragem;
import br.pucmg.sigam.monitoramento.application.domain.barragem.models.TipoBarragem;
import br.pucmg.sigam.monitoramento.infra.dataproviders.repositories.BarragemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BarragemService {
    @Autowired
    private BarragemRepository repository;

    @Autowired
    private BarragemMapper mapper;

    public List<BarragemResponseDTO> getAllBarragens() {
        return mapper.convertListBarragemEntityToListBarragemResponseDTO(repository.findAll());
    }

    public BarragemResponseDTO saveNewBarragem(final BarragemRequestDTO requestDTO) {
        var barragem = repository.save(mapper.convertBarragemRequestDTOToBarragemEntity(requestDTO));

        return mapper.convertBarragemEntityToBarragemResponseDTO(barragem);
    }

    public BarragemResponseDTO editBarragem(final Long id, final BarragemRequestDTO requestDTO) throws Exception {
        var barragemSolicitada = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com o ID: " + id));

        barragemSolicitada.setNome(requestDTO.getNome());
        barragemSolicitada.setTipo(TipoBarragem.valueOf(requestDTO.getTipo()));
        barragemSolicitada.setMunicipio(requestDTO.getMunicipio());
        barragemSolicitada.setEstado(requestDTO.getEstado());
        barragemSolicitada.setRisco(ClassificacaoRisco.valueOf(requestDTO.getRisco()));
        barragemSolicitada.setStatus(StatusBarragem.valueOf(requestDTO.getStatus()));

        var barragemAtualizada = repository.save(barragemSolicitada);

        return mapper.convertBarragemEntityToBarragemResponseDTO(barragemAtualizada);
    }

    public void deleteBarragemById(final Long id) throws Exception {
        var barragem = repository.findById(id).orElseThrow(() -> new Exception());

        repository.delete(barragem);
    }
}