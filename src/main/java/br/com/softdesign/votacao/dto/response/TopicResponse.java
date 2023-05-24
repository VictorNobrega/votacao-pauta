package br.com.softdesign.votacao.dto.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class TopicResponse {

    private Long id;
    private String description;
    private boolean ableOpenSession;
}
