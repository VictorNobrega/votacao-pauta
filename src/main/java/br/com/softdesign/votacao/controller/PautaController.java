package br.com.softdesign.votacao.controller;

import br.com.softdesign.votacao.dto.PautaRequest;
import br.com.softdesign.votacao.dto.PautaResponse;
import br.com.softdesign.votacao.service.PautaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/pauta")
public class PautaController {

    @Autowired
    private PautaService pautaService;

    @RequestMapping(method = RequestMethod.POST)
    private ResponseEntity<Void> criarPauta(@Valid @RequestBody PautaRequest pautaRequest) {
        pautaService.criarPauta(pautaRequest);
        return null;
    }

    @RequestMapping(value = "/{pautaId}", method = RequestMethod.GET)
    private ResponseEntity<PautaResponse> resultadoPauta(@PathVariable("pautaId") Long pautaId) {
        return new ResponseEntity<>(pautaService.resultadoPauta(pautaId), HttpStatus.OK);
    }

}
