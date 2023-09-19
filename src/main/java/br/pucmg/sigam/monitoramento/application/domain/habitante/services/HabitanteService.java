package br.pucmg.sigam.monitoramento.application.domain.habitante.services;

import br.pucmg.sigam.monitoramento.api.dtos.HabitanteRequestDTO;
import br.pucmg.sigam.monitoramento.api.dtos.HabitanteResponseDTO;
import br.pucmg.sigam.monitoramento.application.domain.habitante.mappers.HabitanteMapper;
import br.pucmg.sigam.monitoramento.infra.dataproviders.repositories.EnderecoRepository;
import br.pucmg.sigam.monitoramento.infra.dataproviders.repositories.HabitanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HabitanteService {
    @Autowired
    private HabitanteRepository habitanteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private HabitanteMapper mapper;

    public List<HabitanteResponseDTO> getAllHabitantes() {
        return mapper.convertListHabitanteEntityToListHabitanteResponseDTO(habitanteRepository.findAll());
    }

    public HabitanteResponseDTO saveNewHabitante(final HabitanteRequestDTO requestDTO) {
        var barragem = habitanteRepository.save(mapper.convertHabitanteRequestDTOToHabitanteEntity(requestDTO));

        return mapper.convertHabitanteEntityToHabitanteResponseDTO(barragem);
    }

    public HabitanteResponseDTO editHabitante(final Long id, final HabitanteRequestDTO requestDTO) throws Exception {
        var habitanteSolicitado = habitanteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Habitante não encontrado com o ID: " + id));

        habitanteSolicitado.getEndereco().setCep(requestDTO.getEndereco().getCep());
        habitanteSolicitado.getEndereco().setLogradouro(requestDTO.getEndereco().getLogradouro());
        habitanteSolicitado.getEndereco().setNumero(requestDTO.getEndereco().getNumero());
        habitanteSolicitado.getEndereco().setComplemento(requestDTO.getEndereco().getComplemento());
        habitanteSolicitado.getEndereco().setMunicipio(requestDTO.getEndereco().getMunicipio());
        habitanteSolicitado.getEndereco().setEstado(requestDTO.getEndereco().getEstado());
        habitanteSolicitado.setNome(requestDTO.getNome());
        habitanteSolicitado.setNascimento(requestDTO.getNascimento());
        habitanteSolicitado.setTelefone(requestDTO.getTelefone());
        habitanteSolicitado.setEmail(requestDTO.getEmail());

        var habitanteAtualizado = habitanteRepository.save(habitanteSolicitado);

        return mapper.convertHabitanteEntityToHabitanteResponseDTO(habitanteAtualizado);
    }

    public void deleteHabitanteById(final Long id) throws Exception {
        var habitante = habitanteRepository.findById(id).orElseThrow(() -> new Exception());

        enderecoRepository.delete(habitante.getEndereco());

        habitanteRepository.delete(habitante);
    }
}
