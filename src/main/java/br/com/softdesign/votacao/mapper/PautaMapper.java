package br.com.softdesign.votacao.mapper;

import br.com.softdesign.votacao.dto.PautaRequest;
import br.com.softdesign.votacao.model.Pauta;
import org.springframework.stereotype.Component;

@Component
public class PautaMapper {

    public static Pauta mapper(PautaRequest pautaRequest) {

        Pauta pauta = new Pauta();
        pauta.setDescricao(pautaRequest.getDescricao());

        return pauta;
    }
}
