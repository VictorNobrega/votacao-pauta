package br.com.softdesign.votacao.mapper;

import br.com.softdesign.votacao.dto.VoteRequest;
import br.com.softdesign.votacao.enums.VotingOptions;
import br.com.softdesign.votacao.exceptions.InvalidVoteException;
import br.com.softdesign.votacao.model.Topic;
import br.com.softdesign.votacao.model.Vote;
import org.springframework.stereotype.Component;

@Component
public class VotoMapper {

    public static Vote mapper(VoteRequest voteRequest, Topic topic) throws InvalidVoteException {

        Vote vote = new Vote();
        vote.setVotingOptions(VotingOptions.fromString(voteRequest.getVote()));
        vote.setAssociatedId(voteRequest.getAssociateId());
        vote.setTopic(topic);

        return vote;
    }
}
