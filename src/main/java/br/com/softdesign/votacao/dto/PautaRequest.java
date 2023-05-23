package br.com.softdesign.votacao.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class PautaRequest {

    @NotBlank(message = "O campo 'descricao' nao pode ser vazio.")
    private String descricao;

}
