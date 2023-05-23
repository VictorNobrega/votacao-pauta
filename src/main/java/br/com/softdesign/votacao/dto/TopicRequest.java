package br.com.softdesign.votacao.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class TopicRequest {

    @NotBlank(message = "O campo 'description' nao pode ser vazio.")
    private String description;

}
