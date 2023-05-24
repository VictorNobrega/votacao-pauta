package br.com.softdesign.votacao.controller;

import br.com.softdesign.votacao.dto.response.MessageResponse;
import br.com.softdesign.votacao.dto.request.VoteRequest;
import br.com.softdesign.votacao.service.VoteService;
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
@RequestMapping("/vote")
public class VoteController {

    @Autowired
    private VoteService voteService;

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna mensagem confirmando que o Voto foi aceito."),
            @ApiResponse(code = 400, message = "Caso a Pauta informada não existe, a Sessão esteja invalida, " +
                    "o associado já tenha votado ou o Voto seja invalido."),
            @ApiResponse(code = 500, message = "Erro interno.")
    })
    @ApiOperation(value = "Endpoint para votar em uma pauta.")
    @RequestMapping(method = RequestMethod.POST, produces="application/json", consumes="application/json")
    private ResponseEntity<MessageResponse> createVote(@Valid @RequestBody VoteRequest voteRequest) {
        try {
            return new ResponseEntity<>(voteService.createVote(voteRequest), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

}
