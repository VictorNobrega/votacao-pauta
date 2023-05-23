package br.com.softdesign.votacao.service;

import br.com.softdesign.votacao.dto.MensagemResponse;
import br.com.softdesign.votacao.dto.SessaoRequest;
import br.com.softdesign.votacao.exceptions.PautaNaoEncontradaException;
import br.com.softdesign.votacao.exceptions.SessaoInvalidaException;
import br.com.softdesign.votacao.mapper.SessaoMapper;
import br.com.softdesign.votacao.model.Pauta;
import br.com.softdesign.votacao.model.Sessao;
import br.com.softdesign.votacao.repository.SessaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SessaoService {

    @Autowired
    private SessaoRepository sessaoRepository;

    @Autowired
    private PautaService pautaService;

    public MensagemResponse criarSessao(SessaoRequest sessaoRequest) throws PautaNaoEncontradaException {

        Pauta pauta = pautaService.buscarPauta(sessaoRequest.getIdPauta());
        Sessao sessao = SessaoMapper.mapper(sessaoRequest);

        Sessao novaSessao = sessaoRepository.save(sessao);

        pautaService.atualizarPautaComSessao(pauta, novaSessao);

        return new MensagemResponse("Sessão criada com sucesso.");
    }

    public void isSessaoValida(Sessao sessao) throws SessaoInvalidaException {

        LocalDateTime horarioAtual = LocalDateTime.now();

        boolean isSessaoValida = horarioAtual.isEqual(sessao.getInicio()) || horarioAtual.isEqual(sessao.getFim())
                || (horarioAtual.isAfter(sessao.getInicio()) && horarioAtual.isBefore(sessao.getFim()));

        if (!isSessaoValida) {
            throw new SessaoInvalidaException("A Sessão de voto não está disponivel");
        }
    }

}
