package br.com.softdesign.votacao.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ResultadoPautaResponse {

    private Long id;
    private String descricao;
    private String resultado;
    private long votosSim;
    private long votosNao;

}
