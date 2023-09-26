package br.pucmg.sigam.monitoramento.application.domain.barragem.mappers;

import br.pucmg.sigam.monitoramento.api.dtos.BarragemRequestDTO;
import br.pucmg.sigam.monitoramento.api.dtos.BarragemResponseDTO;
import br.pucmg.sigam.monitoramento.application.domain.barragem.models.Barragem;
import br.pucmg.sigam.monitoramento.application.domain.barragem.models.ClassificacaoRisco;
import br.pucmg.sigam.monitoramento.application.domain.barragem.models.StatusBarragem;
import br.pucmg.sigam.monitoramento.application.domain.barragem.models.TipoBarragem;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BarragemMapper {
    public Barragem convertBarragemRequestDTOToBarragemEntity(final BarragemRequestDTO requestDTO) {
        return Barragem.builder()
                .nome(requestDTO.getNome())
                .tipo(TipoBarragem.valueOf(requestDTO.getTipo()))
                .municipio(requestDTO.getMunicipio())
                .estado(requestDTO.getEstado())
                .risco(ClassificacaoRisco.valueOf(requestDTO.getRisco()))
                .status(StatusBarragem.valueOf(requestDTO.getStatus()))
                .build();
    }

    public BarragemResponseDTO convertBarragemEntityToBarragemResponseDTO(final Barragem barragem) {
        return BarragemResponseDTO.builder()
                .id(barragem.getId())
                .nome(barragem.getNome())
                .tipo(barragem.getTipo().getTipo())
                .municipio(barragem.getMunicipio())
                .estado(barragem.getEstado())
                .risco(barragem.getRisco().getRisco())
                .status(barragem.getStatus().getStatus())
                .build();
    }

    public List<BarragemResponseDTO> convertListBarragemEntityToListBarragemResponseDTO(final List<Barragem> barragens) {
        List barragensResponseDTO = new ArrayList();

        for (Barragem barragem: barragens) {
            barragensResponseDTO.add(convertBarragemEntityToBarragemResponseDTO(barragem));
        }

        return barragensResponseDTO;
    }
}
