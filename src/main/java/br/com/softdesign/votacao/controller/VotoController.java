package br.com.softdesign.votacao.controller;

import br.com.softdesign.votacao.dto.MensagemResponse;
import br.com.softdesign.votacao.dto.VotoRequest;
import br.com.softdesign.votacao.exceptions.PautaNaoEncontradaException;
import br.com.softdesign.votacao.service.VotoService;
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
@RequestMapping("/voto")
public class VotoController {

    @Autowired
    private VotoService votoService;

    @RequestMapping(method = RequestMethod.POST)
    private ResponseEntity<MensagemResponse> criarVoto(@Valid @RequestBody VotoRequest votoRequest) {
        try {
            return new ResponseEntity<>(votoService.criarVoto(votoRequest), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

}
