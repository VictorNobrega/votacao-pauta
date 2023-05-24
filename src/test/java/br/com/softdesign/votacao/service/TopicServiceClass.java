package br.com.softdesign.votacao.service;

import br.com.softdesign.votacao.dto.response.MessageResponse;
import br.com.softdesign.votacao.dto.request.TopicRequest;
import br.com.softdesign.votacao.dto.response.TopicResultResponse;
import br.com.softdesign.votacao.enums.VotingOptions;
import br.com.softdesign.votacao.exceptions.TopicNotFoundException;
import br.com.softdesign.votacao.model.Session;
import br.com.softdesign.votacao.model.Topic;
import br.com.softdesign.votacao.model.Vote;
import br.com.softdesign.votacao.repository.TopicRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class TopicServiceClass {

    @Mock
    private TopicRepository topicRepository;

    @InjectMocks
    private TopicService topicService;

    @Test
    public void createTopicTest() {
        Mockito.when(topicRepository.save(Mockito.any())).thenReturn(getTopic());

        assertEquals(topicService.createTopic(getTopicRequest()), getMessageResponseExpected());
    }

    @Test
    public void searchTopicResultTopicNotFoundExceptionTest() {
        assertThrows(TopicNotFoundException.class, () -> topicService.searchTopicResult(Mockito.anyLong()));
    }

    @Test
    public void searchTopicResultTest() throws TopicNotFoundException {
        Mockito.when(topicRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(getTopic()));

        TopicResultResponse topicResultResponse = topicService.searchTopicResult(Mockito.anyLong());

        assertEquals(topicResultResponse.getId(), getTopic().getId());
        assertEquals(topicResultResponse.getDescription(), getTopic().getDescription());
        assertEquals(topicResultResponse.getResult(), "Empate");
    }

    @Test
    public void searchTopicResultYesTest() throws TopicNotFoundException {
        Topic topic = getTopic();
        topic.setVotes(Collections.singletonList(getVote(VotingOptions.SIM)));

        Mockito.when(topicRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(topic));

        TopicResultResponse topicResultResponse = topicService.searchTopicResult(Mockito.anyLong());

        assertEquals(topicResultResponse.getId(), getTopic().getId());
        assertEquals(topicResultResponse.getDescription(), getTopic().getDescription());
        assertEquals(topicResultResponse.getResult(), "Sim");
    }

    @Test
    public void searchTopicResultNoTest() throws TopicNotFoundException {
        Topic topic = getTopic();
        topic.setVotes(Collections.singletonList(getVote(VotingOptions.NAO)));

        Mockito.when(topicRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(topic));

        TopicResultResponse topicResultResponse = topicService.searchTopicResult(Mockito.anyLong());

        assertEquals(topicResultResponse.getId(), getTopic().getId());
        assertEquals(topicResultResponse.getDescription(), getTopic().getDescription());
        assertEquals(topicResultResponse.getResult(), "Não");
    }



    private Vote getVote(VotingOptions votingOptions) {
        Vote vote = new Vote();
        vote.setTopic(getTopic());
        vote.setVotingOptions(votingOptions);
        vote.setId(1L);
        vote.setAssociatedId("12345678901");

        return vote;
    }

    private Topic getTopic() {
        Topic topic = new Topic();
        topic.setId(1L);
        topic.setDescription("Pauta para teste.");
        topic.setSession(getSession());
        topic.setVotes(Collections.emptyList());

        return topic;
    }

    private TopicRequest getTopicRequest() {
        return TopicRequest.builder()
                .description("Pauta para teste.")
                .build();
    }

    private MessageResponse getMessageResponseExpected() {
        return new MessageResponse(String.format("Pauta criada com sucesso. " +
                "Para abrir uma sessão de voto e para votar, utilize o id: %d", getTopic().getId()));
    }

    private Session getSession() {
        Session session = new Session();
        session.setId(1L);
        session.setStartTime(LocalDateTime.now());
        session.setEndTime(LocalDateTime.now().plusMinutes(3));

        return session;
    }
}
