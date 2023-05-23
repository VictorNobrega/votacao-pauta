package br.com.softdesign.votacao.mapper;

import br.com.softdesign.votacao.dto.SessionRequest;
import br.com.softdesign.votacao.model.Session;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class SessionMapper {

    public static Session mapper(SessionRequest sessionRepository) {

        Session session = new Session();
        session.setStartTime(LocalDateTime.now());
        session.setEndTime(LocalDateTime.now().plusMinutes(sessionRepository.getTopicTimeMinutes()));

        return session;
    }
}
