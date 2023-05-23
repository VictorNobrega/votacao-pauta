package br.com.softdesign.votacao.controller;

import br.com.softdesign.votacao.dto.MensagemResponse;
import br.com.softdesign.votacao.dto.PautaRequest;
import br.com.softdesign.votacao.dto.PautaResponse;
import br.com.softdesign.votacao.dto.ResultadoPautaResponse;
import br.com.softdesign.votacao.service.PautaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/pauta")
public class PautaController {

    @Autowired
    private PautaService pautaService;

    @RequestMapping(method = RequestMethod.POST)
    private ResponseEntity<MensagemResponse> criarPauta(@Valid @RequestBody PautaRequest pautaRequest) {
        return new ResponseEntity<>(pautaService.criarPauta(pautaRequest), HttpStatus.OK);
    }

    @RequestMapping(value = "/{pautaId}", method = RequestMethod.GET)
    private ResponseEntity<ResultadoPautaResponse> buscarResultadoPauta(@PathVariable("pautaId") Long pautaId) {
        try {
            return new ResponseEntity<>(pautaService.buscarResultadoPauta(pautaId), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @RequestMapping(value = "/infos", method = RequestMethod.GET)
    private ResponseEntity<List<PautaResponse>> buscarPautasInfos() {
        try {
            return new ResponseEntity<>(pautaService.buscarPautasInfos(), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

}
