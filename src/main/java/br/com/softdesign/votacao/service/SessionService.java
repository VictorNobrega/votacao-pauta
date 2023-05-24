package br.com.softdesign.votacao.service;

import br.com.softdesign.votacao.dto.response.MessageResponse;
import br.com.softdesign.votacao.dto.request.SessionRequest;
import br.com.softdesign.votacao.exceptions.TopicNotFoundException;
import br.com.softdesign.votacao.exceptions.InvalidSessionException;
import br.com.softdesign.votacao.exceptions.SessionCreatedException;
import br.com.softdesign.votacao.mapper.SessionMapper;
import br.com.softdesign.votacao.model.Topic;
import br.com.softdesign.votacao.model.Session;
import br.com.softdesign.votacao.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class SessionService {

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private TopicService topicService;

    public MessageResponse createSession(SessionRequest sessionRequest) throws TopicNotFoundException,
            SessionCreatedException {

        Topic topic = topicService.searchTopic(sessionRequest.getTopicId());

        if (Objects.nonNull(topic.getSession())) {
            throw new SessionCreatedException("A pauta informada já possui um sessão aberta, ou já foi votada.");
        }

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
