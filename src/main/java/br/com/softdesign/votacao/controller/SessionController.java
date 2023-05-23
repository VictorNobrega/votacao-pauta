package br.com.softdesign.votacao.controller;

import br.com.softdesign.votacao.dto.MessageResponse;
import br.com.softdesign.votacao.dto.SessionRequest;
import br.com.softdesign.votacao.service.SessionService;
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
@RequestMapping("/session")
public class SessionController {

    @Autowired
    private SessionService sessionService;

    @RequestMapping(method = RequestMethod.POST)
    private ResponseEntity<MessageResponse> createSession(@Valid @RequestBody SessionRequest sessionRequest) {
        try {
            return new ResponseEntity<>(sessionService.createSession(sessionRequest), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
