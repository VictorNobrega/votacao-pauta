package br.com.softdesign.votacao.mapper;

import br.com.softdesign.votacao.dto.SessaoRequest;
import br.com.softdesign.votacao.model.Pauta;
import br.com.softdesign.votacao.model.Sessao;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class SessaoMapper {

    public static Sessao mapper(SessaoRequest sessaoRepository, Pauta pauta) {

        Sessao sessao = new Sessao();
        sessao.setInicio(LocalDateTime.now());
        sessao.setFim(LocalDateTime.now().plusMinutes(sessaoRepository.getTempoPautaMinutos()));

        return sessao;
    }
}
