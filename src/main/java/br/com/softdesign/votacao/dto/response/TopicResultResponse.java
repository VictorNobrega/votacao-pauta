package br.com.softdesign.votacao.dto.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class TopicResultResponse {

    private Long id;
    private String description;
    private String result;
    private long numberYesVotes;
    private long numberNoVotes;

}
