package br.com.softdesign.votacao.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PautaResponse {

    private Long id;
    private String descricao;
    private boolean aptaParaSessao;
}
