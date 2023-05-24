package br.com.softdesign.votacao.controller;

import br.com.softdesign.votacao.dto.response.MessageResponse;
import br.com.softdesign.votacao.dto.request.TopicRequest;
import br.com.softdesign.votacao.dto.response.TopicResponse;
import br.com.softdesign.votacao.dto.response.TopicResultResponse;
import br.com.softdesign.votacao.service.TopicService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/topic")
public class TopicController {

    @Autowired
    private TopicService topicService;

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna mensagem confirmando a criação de uma pauta."),
            @ApiResponse(code = 500, message = "Erro interno.")
    })
    @ApiOperation(value = "Endpoint para criar uma pauta para ser votada.")
    @RequestMapping(method = RequestMethod.POST, produces="application/json", consumes="application/json")
    private ResponseEntity<MessageResponse> createTopic(@Valid @RequestBody TopicRequest topicRequest) {
        return new ResponseEntity<>(topicService.createTopic(topicRequest), HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna json com informações sobre a Pauta e a votação."),
            @ApiResponse(code = 400, message = "A Pauta informada não existe."),
            @ApiResponse(code = 500, message = "Erro interno.")
    })
    @ApiOperation(value = "Endpoint para verificar resultados de uma Pauta.")
    @RequestMapping(value = "/{topicId}", method = RequestMethod.GET, produces="application/json")
    private ResponseEntity<TopicResultResponse> searchTopicResult(@PathVariable("topicId") Long topicId) {
        try {
            return new ResponseEntity<>(topicService.searchTopicResult(topicId), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna json com informações de sobre a Pauta e se está disponível para votação."),
            @ApiResponse(code = 500, message = "Erro interno.")
    })
    @ApiOperation(value = "Endpoint para verificar informações de uma Pauta.")
    @RequestMapping(value = "/infos", method = RequestMethod.GET, produces="application/json")
    private ResponseEntity<List<TopicResponse>> searchInfoTopic() {
        return new ResponseEntity<>(topicService.searchInfoTopic(), HttpStatus.OK);
    }

}
