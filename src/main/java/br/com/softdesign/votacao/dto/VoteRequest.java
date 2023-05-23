package br.com.softdesign.votacao.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class VoteRequest {

    @NotNull(message = "O campo 'topicId' não pode ser nulo.")
    private Long topicId;

    @NotBlank(message = "O campo 'associateId' nao pode ser vazio.")
    private String associateId;

    @NotBlank(message = "O campo 'vote' nao pode ser vazio.")
    private String vote;

}
