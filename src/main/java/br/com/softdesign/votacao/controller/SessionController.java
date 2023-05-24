package br.com.softdesign.votacao.controller;

import br.com.softdesign.votacao.dto.response.MessageResponse;
import br.com.softdesign.votacao.dto.request.SessionRequest;
import br.com.softdesign.votacao.service.SessionService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna mensagem confirmando a criação de uma Sessão."),
            @ApiResponse(code = 400, message = "A Pauta não existe ou a Sessão para votação já foi aberta."),
            @ApiResponse(code = 500, message = "Erro interno.")
    })
    @ApiOperation(value = "Endpoint para criar uma Sessão de votação para uma Pauta cadastrada.")
    @RequestMapping(method = RequestMethod.POST, produces="application/json", consumes="application/json")
    private ResponseEntity<MessageResponse> createSession(@Valid @RequestBody SessionRequest sessionRequest) {
        try {
            return new ResponseEntity<>(sessionService.createSession(sessionRequest), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
