package br.com.softdesign.votacao.service;

import br.com.softdesign.votacao.dto.response.MessageResponse;
import br.com.softdesign.votacao.dto.request.VoteRequest;
import br.com.softdesign.votacao.exceptions.MemberHasVoteException;
import br.com.softdesign.votacao.exceptions.TopicNotFoundException;
import br.com.softdesign.votacao.exceptions.InvalidSessionException;
import br.com.softdesign.votacao.exceptions.InvalidVoteException;
import br.com.softdesign.votacao.mapper.VotoMapper;
import br.com.softdesign.votacao.model.Topic;
import br.com.softdesign.votacao.model.Vote;
import br.com.softdesign.votacao.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoteService {

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private TopicService topicService;

    @Autowired
    private SessionService sessionService;

    public MessageResponse createVote(VoteRequest voteRequest) throws TopicNotFoundException,
            InvalidSessionException, MemberHasVoteException, InvalidVoteException {

        Topic topic = topicService.searchTopic(voteRequest.getTopicId());

        sessionService.isValidSession(topic.getSession());
        hasMemberVoted(voteRequest, topic);

        Vote vote = VotoMapper.mapper(voteRequest, topic);
        Vote newVote = voteRepository.save(vote);

        topicService.updateTopicVotes(topic, newVote);

        return new MessageResponse("Voto registrado com sucesso.");
    }

    private void hasMemberVoted(VoteRequest voteRequest, Topic topic) throws MemberHasVoteException {
        boolean hasMemberVoted = topic.getVotes().stream()
                .anyMatch(vote -> vote.getAssociatedId().equals(voteRequest.getAssociateId()));

        if (hasMemberVoted) {
            throw new MemberHasVoteException("O associado já possui o voto registrado.");
        }
    }
}