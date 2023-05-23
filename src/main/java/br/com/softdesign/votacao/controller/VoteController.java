package br.com.softdesign.votacao.controller;

import br.com.softdesign.votacao.dto.MessageResponse;
import br.com.softdesign.votacao.dto.VoteRequest;
import br.com.softdesign.votacao.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/vote")
public class VoteController {

    @Autowired
    private VoteService voteService;

    @RequestMapping(method = RequestMethod.POST)
    private ResponseEntity<MessageResponse> createVote(@Valid @RequestBody VoteRequest voteRequest) {
        try {
            return new ResponseEntity<>(voteService.createVote(voteRequest), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

}
