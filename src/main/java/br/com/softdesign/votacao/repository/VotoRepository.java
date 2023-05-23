package br.com.softdesign.votacao.repository;

import br.com.softdesign.votacao.model.Voto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VotoRepository extends CrudRepository<Voto, Long> {
}
