package br.com.softdesign.votacao.mapper;

import br.com.softdesign.votacao.dto.TopicRequest;
import br.com.softdesign.votacao.dto.TopicResponse;
import br.com.softdesign.votacao.dto.TopicResultResponse;
import br.com.softdesign.votacao.enums.VotingOptions;
import br.com.softdesign.votacao.model.Topic;
import br.com.softdesign.votacao.model.Vote;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class TopicMapper {

    public static Topic mapper(TopicRequest topicRequest) {

        Topic topic = new Topic();
        topic.setDescription(topicRequest.getDescription());

        return topic;
    }

    public static TopicResultResponse mapper(Topic topic) {

        long numberYesVotes = getNumberYesVotes(topic);
        long numberNoVotes = getNumberNoVotes(topic.getVotes(), numberYesVotes);

        return TopicResultResponse.builder()
                .id(topic.getId())
                .description(topic.getDescription())
                .result(getResult(numberYesVotes, numberNoVotes))
                .numberNoVotes(numberNoVotes)
                .numberYesVotes(numberYesVotes)
                .build();
    }

    private static String getResult(long numberYesVotes, long numberNoVotes) {

        String result;

        if (numberYesVotes == numberNoVotes) {
            result = "Empate";
        } else {
            result = numberYesVotes > numberNoVotes ? "Sim" : "Não";
        }

        return result;
    }

    private static long getNumberNoVotes(List<Vote> votes, long numberYesVotes) {
        return votes.size() - numberYesVotes;
    }

    private static long getNumberYesVotes(Topic topic) {
        return topic.getVotes().stream()
                .filter(vote -> VotingOptions.SIM.equals(vote.getVotingOptions()))
                .count();
    }

    public static List<TopicResponse> mapper(List<Topic> topics) {
        return topics.stream()
                .map(topic -> convertToTopicResponse(topic))
                .collect(Collectors.toList());

    }

    private static TopicResponse convertToTopicResponse(Topic topic) {
        return TopicResponse.builder()
                .id(topic.getId())
                .description(topic.getDescription())
                .ableOpenSession(Objects.isNull(topic.getSession()))
                .build();
    }
}
