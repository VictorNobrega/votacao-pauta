package br.com.softdesign.votacao.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
public class VoteRequest {

    @NotNull(message = "O campo 'topicId' não pode ser nulo.")
    private Long topicId;

    @NotBlank(message = "O campo 'associateId' não pode ser vazio.")
    private String associateId;

    @NotBlank(message = "O campo 'vote' não pode ser vazio.")
    private String vote;

}
