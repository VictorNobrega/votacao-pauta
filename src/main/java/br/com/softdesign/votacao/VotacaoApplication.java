package br.com.softdesign.votacao;

import br.com.softdesign.votacao.model.Voto;
import br.com.softdesign.votacao.repository.VotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class VotacaoApplication {

	@Autowired
	private VotoRepository votoRepository;
	public static void main(String[] args) {
		SpringApplication.run(VotacaoApplication.class, args);
	}

}
