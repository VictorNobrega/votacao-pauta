package br.com.softdesign.votacao.controller;

import br.com.softdesign.votacao.dto.SessaoRequest;
import br.com.softdesign.votacao.service.SessaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/sessao")
public class SessaoController {

    @Autowired
    private SessaoService sessaoService;

    @RequestMapping(method = RequestMethod.POST)
    private ResponseEntity<Void> criarSessao(@Valid @RequestBody SessaoRequest sessaoRequest) {

        sessaoService.criarSessao(sessaoRequest);
        return null;
    }
}
