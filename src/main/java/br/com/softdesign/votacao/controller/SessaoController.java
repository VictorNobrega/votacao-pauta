package br.com.softdesign.votacao.controller;

import br.com.softdesign.votacao.dto.MensagemResponse;
import br.com.softdesign.votacao.dto.SessaoRequest;
import br.com.softdesign.votacao.exceptions.PautaNaoEncontradaException;
import br.com.softdesign.votacao.service.SessaoService;
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
@RequestMapping("/sessao")
public class SessaoController {

    @Autowired
    private SessaoService sessaoService;

    @RequestMapping(method = RequestMethod.POST)
    private ResponseEntity<MensagemResponse> criarSessao(@Valid @RequestBody SessaoRequest sessaoRequest) {
        try {
            return new ResponseEntity<>(sessaoService.criarSessao(sessaoRequest), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
