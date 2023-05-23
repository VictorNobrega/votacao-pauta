package br.com.softdesign.votacao.service;

import br.com.softdesign.votacao.dto.PautaRequest;
import br.com.softdesign.votacao.mapper.PautaMapper;
import br.com.softdesign.votacao.model.Pauta;
import br.com.softdesign.votacao.model.Sessao;
import br.com.softdesign.votacao.repository.PautaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PautaService {

    @Autowired
    private PautaRepository pautaRepository;

    public void criarPauta(PautaRequest pautaRequest) {
        Pauta pauta = PautaMapper.mapper(pautaRequest);

        pautaRepository.save(pauta);
    }

    public Pauta buscarPauta(Long pautaId) {
        return pautaRepository.findById(pautaId)
                .get();
    }

    public void atualizarPautaComSessao(Pauta pauta, Sessao sessaoPersistida) {
        pauta.setSessao(sessaoPersistida);

        pautaRepository.save(pauta);
    }
}
