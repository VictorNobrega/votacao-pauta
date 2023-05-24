package br.com.softdesign.votacao.service;

import br.com.softdesign.votacao.dto.request.VoteRequest;
import br.com.softdesign.votacao.dto.response.MessageResponse;
import br.com.softdesign.votacao.enums.VotingOptions;
import br.com.softdesign.votacao.exceptions.InvalidSessionException;
import br.com.softdesign.votacao.exceptions.InvalidVoteException;
import br.com.softdesign.votacao.exceptions.MemberHasVoteException;
import br.com.softdesign.votacao.exceptions.TopicNotFoundException;
import br.com.softdesign.votacao.model.Session;
import br.com.softdesign.votacao.model.Topic;
import br.com.softdesign.votacao.model.Vote;
import br.com.softdesign.votacao.repository.VoteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class VoteServiceTest {

    @Mock
    private VoteRepository voteRepository;

    @Mock
    private TopicService topicService;

    @Mock
    private SessionService sessionService;

    @InjectMocks
    private VoteService voteService;

    @Test
    public void createVoteTopicNotFoundExceptionTest() throws TopicNotFoundException {
        Mockito.when(topicService.searchTopic(Mockito.anyLong())).thenThrow(TopicNotFoundException.class);

        assertThrows(TopicNotFoundException.class, () -> voteService.createVote(getVoteRequest()));
    }

    @Test
    public void createVoteMemberHasVotdExceptionTest() throws TopicNotFoundException {
        Topic topic = getTopic();
        topic.setVotes(Collections.singletonList(getVote()));

        Mockito.when(topicService.searchTopic(Mockito.anyLong())).thenReturn(topic);

        assertThrows(MemberHasVoteException.class, () -> voteService.createVote(getVoteRequest()));
    }

    @Test
    public void createVoteInvalidSessionExceptionTest() throws TopicNotFoundException {
        Topic topic = getTopic();
        topic.setVotes(Collections.singletonList(getVote()));

        Mockito.when(topicService.searchTopic(Mockito.anyLong())).thenReturn(topic);

        assertThrows(MemberHasVoteException.class, () -> voteService.createVote(getVoteRequest()));
    }

    @Test
    public void createVoteTest() throws TopicNotFoundException {
        Mockito.when(topicService.searchTopic(Mockito.anyLong())).thenReturn(getTopic());

        assertThrows(InvalidVoteException.class, () -> voteService.createVote(getVoteInvalidRequest()));
    }

    @Test
    public void createVoteInvalidVoteExceptionTest() throws TopicNotFoundException, InvalidSessionException, InvalidVoteException, MemberHasVoteException {
        Mockito.when(topicService.searchTopic(Mockito.anyLong())).thenReturn(getTopic());

        assertEquals(voteService.createVote(getVoteRequest()), getMessageResponseExpected());
    }

    private VoteRequest getVoteInvalidRequest() {
        return VoteRequest.builder()
                .vote("Invalido")
                .associateId("12345678901")
                .topicId(1L)
                .build();
    }

    private Vote getVote() {
        Vote vote = new Vote();
        vote.setVotingOptions(VotingOptions.SIM);
        vote.setAssociatedId("12345678901");
        vote.setTopic(getTopic());
        vote.setId(1L);

        return vote;
    }

    private VoteRequest getVoteRequest() {
        return VoteRequest.builder()
                .vote("Sim")
                .associateId("12345678901")
                .topicId(1L)
                .build();
    }

    private Topic getTopic() {
        Topic topic = new Topic();
        topic.setId(1L);
        topic.setDescription("Pauta para teste.");
        topic.setSession(getSession());
        topic.setVotes(Collections.emptyList());

        return topic;
    }

    private Session getSession() {
        Session session = new Session();
        session.setId(1L);
        session.setStartTime(LocalDateTime.now());
        session.setEndTime(LocalDateTime.now().plusMinutes(3));

        return session;
    }

    private Session getInvalidSession() {
        Session session = new Session();

        session.setId(1L);
        session.setStartTime(LocalDateTime.now().minusMinutes(10));
        session.setEndTime(LocalDateTime.now().minusMinutes(5));

        return session;
    }

    private MessageResponse getMessageResponseExpected() {
        return new MessageResponse("Voto registrado com sucesso.");
    }
}
