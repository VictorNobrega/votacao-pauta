package br.com.softdesign.votacao.service;

import br.com.softdesign.votacao.dto.MensagemResponse;
import br.com.softdesign.votacao.dto.PautaRequest;
import br.com.softdesign.votacao.dto.PautaResponse;
import br.com.softdesign.votacao.exceptions.PautaNaoEncontradaException;
import br.com.softdesign.votacao.mapper.PautaMapper;
import br.com.softdesign.votacao.model.Pauta;
import br.com.softdesign.votacao.model.Sessao;
import br.com.softdesign.votacao.model.Voto;
import br.com.softdesign.votacao.repository.PautaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PautaService {

    @Autowired
    private PautaRepository pautaRepository;

    public MensagemResponse criarPauta(PautaRequest pautaRequest) {
        Pauta pauta = PautaMapper.mapper(pautaRequest);

        Pauta pautaCriada = pautaRepository.save(pauta);

        return new MensagemResponse(String.format("Pauta criada com sucesso. " +
                "Para abrir uma sessão de voto e para vota, utilize o id: %d", pautaCriada.getId()));
    }

    public PautaResponse resultadoPauta(Long pautaId) throws PautaNaoEncontradaException {
        Pauta pauta = buscarPauta(pautaId);

        return PautaMapper.mapper(pauta);
    }

    public Pauta buscarPauta(Long pautaId) throws PautaNaoEncontradaException {
        return pautaRepository.findById(pautaId)
                .orElseThrow(() -> new PautaNaoEncontradaException("A Pauta buscada não existe."));
    }

    public void atualizarPautaComSessao(Pauta pauta, Sessao sessaoPersistida) {
        pauta.setSessao(sessaoPersistida);

        pautaRepository.save(pauta);
    }

    public void atualizarVotos(Pauta pauta, Voto votoCriado) {
        pauta.getVotos().add(votoCriado);

        pautaRepository.save(pauta);
    }
}
