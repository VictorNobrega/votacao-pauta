package br.com.softdesign.votacao.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Objects;

@Getter
@Setter
@Builder
public class SessionRequest {

    public static final int TIME_DEFAULT = 1;

    @NotNull(message = "O campo 'topicId' não pode ser nulo.")
    private Long topicId;

    private Integer topicTimeMinutes;

    public Integer getTopicTimeMinutes() {
        return Objects.isNull(topicTimeMinutes) ? TIME_DEFAULT : topicTimeMinutes;
    }

}
