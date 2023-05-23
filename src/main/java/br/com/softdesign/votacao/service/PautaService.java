package br.com.softdesign.votacao.service;

import br.com.softdesign.votacao.dto.PautaRequest;
import br.com.softdesign.votacao.mapper.PautaMapper;
import br.com.softdesign.votacao.model.Pauta;
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
}
