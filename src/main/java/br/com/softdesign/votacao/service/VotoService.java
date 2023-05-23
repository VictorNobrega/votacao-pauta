package br.com.softdesign.votacao.service;

import br.com.softdesign.votacao.dto.MensagemResponse;
import br.com.softdesign.votacao.dto.VotoRequest;
import br.com.softdesign.votacao.exceptions.AssociadoVotouException;
import br.com.softdesign.votacao.exceptions.PautaNaoEncontradaException;
import br.com.softdesign.votacao.exceptions.SessaoInvalidaException;
import br.com.softdesign.votacao.exceptions.VotoInvalidoException;
import br.com.softdesign.votacao.mapper.VotoMapper;
import br.com.softdesign.votacao.model.Pauta;
import br.com.softdesign.votacao.model.Voto;
import br.com.softdesign.votacao.repository.VotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VotoService {

    @Autowired
    private VotoRepository votoRepository;

    @Autowired
    private PautaService pautaService;

    @Autowired
    private SessaoService sessaoService;

    public MensagemResponse criarVoto(VotoRequest votoRequest) throws PautaNaoEncontradaException,
            SessaoInvalidaException, AssociadoVotouException, VotoInvalidoException {

        Pauta pauta = pautaService.buscarPauta(votoRequest.getIdPauta());

        sessaoService.isSessaoValida(pauta.getSessao());
        possuiDireitoAVoto(votoRequest, pauta);

        Voto voto = VotoMapper.mapper(votoRequest, pauta);
        Voto votoCriado = votoRepository.save(voto);

        pautaService.atualizarVotos(pauta, votoCriado);

        return new MensagemResponse("Voto registrado com sucesso.");
    }

    private void possuiDireitoAVoto(VotoRequest votoRequest, Pauta pauta) throws AssociadoVotouException {
        boolean associadoJaVotou = pauta.getVotos().stream()
                .anyMatch(voto -> voto.getIdAssociado().equals(votoRequest.getIdAssociado()));

        if (associadoJaVotou) {
            throw new AssociadoVotouException("O associado já possui o voto registrado.");
        }
    }
}