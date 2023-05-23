package br.com.softdesign.votacao.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class VotoRequest {

    @NotNull(message = "O campo 'idPauta' não pode ser nulo.")
    private Long idPauta;

    @NotBlank(message = "O campo 'idAssociado' nao pode ser vazio.")
    private String idAssociado;

    @NotBlank(message = "O campo 'voto' nao pode ser vazio.")
    private String voto;

}
