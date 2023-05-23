package br.com.softdesign.votacao.service;

import br.com.softdesign.votacao.dto.SessaoRequest;
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

    public void criarSessao(SessaoRequest sessaoRequest) {

        Pauta pauta = pautaService.buscarPauta(sessaoRequest.getIdPauta());
        Sessao sessao = SessaoMapper.mapper(sessaoRequest, pauta);

        Sessao novaSessao = sessaoRepository.save(sessao);

        pautaService.atualizarPautaComSessao(pauta, novaSessao);

    }

    public boolean isSessaoValida(Sessao sessao) {

        LocalDateTime horarioAtual = LocalDateTime.now();

        return horarioAtual.isEqual(sessao.getInicio()) || horarioAtual.isEqual(sessao.getFim())
                || (horarioAtual.isAfter(sessao.getInicio()) && horarioAtual.isBefore(sessao.getFim()));
    }

}
