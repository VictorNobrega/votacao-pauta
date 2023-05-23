package br.com.softdesign.votacao.service;

import br.com.softdesign.votacao.dto.MessageResponse;
import br.com.softdesign.votacao.dto.SessionRequest;
import br.com.softdesign.votacao.exceptions.TopicNotFoundException;
import br.com.softdesign.votacao.exceptions.InvalidSessionException;
import br.com.softdesign.votacao.mapper.SessionMapper;
import br.com.softdesign.votacao.model.Topic;
import br.com.softdesign.votacao.model.Session;
import br.com.softdesign.votacao.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SessionService {

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private TopicService topicService;

    public MessageResponse createSession(SessionRequest sessionRequest) throws TopicNotFoundException {

        Topic topic = topicService.searchTopic(sessionRequest.getTopicId());
        Session session = SessionMapper.mapper(sessionRequest);

        Session newSession = sessionRepository.save(session);

        topicService.updateTopicWithSession(topic, newSession);

        return new MessageResponse("Sessão criada com sucesso.");
    }

    public void isValidSession(Session session) throws InvalidSessionException {

        LocalDateTime currentTime = LocalDateTime.now();

        boolean isValidSession = currentTime.isEqual(session.getStartTime()) || currentTime.isEqual(session.getEndTime())
                || (currentTime.isAfter(session.getStartTime()) && currentTime.isBefore(session.getEndTime()));

        if (!isValidSession) {
            throw new InvalidSessionException("A Sessão de voto não está disponivel");
        }
    }

}
