package br.com.softdesign.votacao.service;

import br.com.softdesign.votacao.dto.VotoRequest;
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

    public void criarVoto(VotoRequest votoRequest) {

        Pauta pauta = pautaService.buscarPauta(votoRequest.getIdPauta());

        if(sessaoService.isSessaoValida(pauta.getSessao())) {

            boolean isVotoValido = pauta.getVotos().stream()
                    .noneMatch(voto -> voto.getIdAssociado().equals(votoRequest.getIdAssociado()));


            if (isVotoValido) {
                Voto voto = VotoMapper.mapper(votoRequest, pauta);

                Voto votoCriado = votoRepository.save(voto);

                pautaService.atualizarVotos(pauta, votoCriado);
            }
        }
    }
}