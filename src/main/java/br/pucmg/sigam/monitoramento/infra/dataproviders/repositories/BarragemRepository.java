package br.pucmg.sigam.monitoramento.infra.dataproviders.repositories;

import br.pucmg.sigam.monitoramento.application.domain.barragem.models.Barragem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BarragemRepository extends JpaRepository<Barragem, Long> {
}
