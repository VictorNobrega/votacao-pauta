package br.com.softdesign.votacao.service;

import br.com.softdesign.votacao.dto.response.MessageResponse;
import br.com.softdesign.votacao.dto.request.SessionRequest;
import br.com.softdesign.votacao.exceptions.InvalidSessionException;
import br.com.softdesign.votacao.exceptions.SessionCreatedException;
import br.com.softdesign.votacao.exceptions.TopicNotFoundException;
import br.com.softdesign.votacao.model.Session;
import br.com.softdesign.votacao.model.Topic;
import br.com.softdesign.votacao.repository.SessionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class SessionServiceTest {

    @Mock
    private SessionRepository sessionRepository;

    @Mock
    private TopicService topicService;

    @InjectMocks
    private SessionService sessionService;

    @Test
    public void createSessionTopicNotFoundExceptionTest() throws TopicNotFoundException {
        Mockito.when(topicService.searchTopic(Mockito.anyLong())).thenThrow(TopicNotFoundException.class);

        assertThrows(TopicNotFoundException.class, () -> sessionService.createSession(getSessionRequest()));
    }

    @Test
    public void createSessionSessionCreatedExceptionTest() throws TopicNotFoundException {
        Mockito.when(topicService.searchTopic(Mockito.anyLong())).thenReturn(getTopic());

        assertThrows(SessionCreatedException.class, () -> sessionService.createSession(getSessionRequest()));
    }

    @Test
    public void createSessionTest() throws TopicNotFoundException, SessionCreatedException {
        Topic topic = getTopic();
        topic.setSession(null);

        Mockito.when(topicService.searchTopic(Mockito.anyLong())).thenReturn(topic);

        assertEquals(sessionService.createSession(getSessionRequest()), getMessageResponseExpected());
    }

    @Test
    public void isValidSessionInvalidSessionExceptionTest() {
        assertThrows(InvalidSessionException.class, () -> sessionService.isValidSession(getInvalidSession()));
    }

    private Session getInvalidSession() {
        Session session = new Session();

        session.setId(1L);
        session.setStartTime(LocalDateTime.now().minusMinutes(10));
        session.setEndTime(LocalDateTime.now().minusMinutes(5));

        return session;
    }

    private MessageResponse getMessageResponseExpected() {
        return new MessageResponse("Sessão criada com sucesso.");
    }

    private Topic getTopic() {
        Topic topic = new Topic();
        topic.setId(1L);
        topic.setDescription("Topico para teste");
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

    private SessionRequest getSessionRequest() {
        return SessionRequest.builder()
                .topicId(1L)
                .topicTimeMinutes(1)
                .build();
    }


}
