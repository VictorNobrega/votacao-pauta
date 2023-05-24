package br.com.softdesign.votacao.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
public class TopicRequest {

    @NotBlank(message = "O campo 'description' nao pode ser vazio.")
    private String description;

}
