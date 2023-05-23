package br.com.softdesign.votacao.repository;

import br.com.softdesign.votacao.model.Pauta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PautaRepository extends JpaRepository<Pauta, Long> {
}
