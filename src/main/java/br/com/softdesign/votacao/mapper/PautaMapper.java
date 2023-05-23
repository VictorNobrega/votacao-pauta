package br.com.softdesign.votacao.mapper;

import br.com.softdesign.votacao.dto.PautaRequest;
import br.com.softdesign.votacao.model.Pauta;

public class PautaMapper {

    public static Pauta mapper(PautaRequest pautaRequest) {
        return Pauta.builder()
                .descricao(pautaRequest.getDescricao())
                .build();
    }
}
