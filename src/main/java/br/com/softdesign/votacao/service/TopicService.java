package br.com.softdesign.votacao.service;

import br.com.softdesign.votacao.dto.response.MessageResponse;
import br.com.softdesign.votacao.dto.request.TopicRequest;
import br.com.softdesign.votacao.dto.response.TopicResponse;
import br.com.softdesign.votacao.dto.response.TopicResultResponse;
import br.com.softdesign.votacao.exceptions.TopicNotFoundException;
import br.com.softdesign.votacao.mapper.TopicMapper;
import br.com.softdesign.votacao.model.Topic;
import br.com.softdesign.votacao.model.Session;
import br.com.softdesign.votacao.model.Vote;
import br.com.softdesign.votacao.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicService {

    @Autowired
    private TopicRepository topicRepository;

    public MessageResponse createTopic(TopicRequest topicRequest) {
        Topic topic = TopicMapper.mapper(topicRequest);

        Topic newTopic = topicRepository.save(topic);

        return new MessageResponse(String.format("Pauta criada com sucesso. " +
                "Para abrir uma sessão de voto e para votar, utilize o id: %d", newTopic.getId()));
    }

    public TopicResultResponse searchTopicResult(Long topicId) throws TopicNotFoundException {
        Topic topic = searchTopic(topicId);

        return TopicMapper.mapper(topic);
    }

    public List<TopicResponse> searchInfoTopic() {
        List<Topic> topics = topicRepository.findAll();

        return TopicMapper.mapper(topics);
    }

    public Topic searchTopic(Long topicId) throws TopicNotFoundException {
        return topicRepository.findById(topicId)
                .orElseThrow(() -> new TopicNotFoundException("A Pauta buscada não existe."));
    }

    public void updateTopicWithSession(Topic topic, Session newSession) {
        topic.setSession(newSession);

        topicRepository.save(topic);
    }

    public void updateTopicVotes(Topic topic, Vote newVote) {
        topic.getVotes().add(newVote);

        topicRepository.save(topic);
    }
}
