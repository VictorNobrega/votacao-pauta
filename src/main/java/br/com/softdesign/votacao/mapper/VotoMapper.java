package br.com.softdesign.votacao.mapper;

import br.com.softdesign.votacao.dto.VotoRequest;
import br.com.softdesign.votacao.enums.OpcaoVoto;
import br.com.softdesign.votacao.model.Pauta;
import br.com.softdesign.votacao.model.Voto;
import org.springframework.stereotype.Component;

@Component
public class VotoMapper {

    public static Voto mapper(VotoRequest votoRequest, Pauta pauta) {

        Voto voto = new Voto();
        voto.setOpcaoVoto(OpcaoVoto.fromString(votoRequest.getVoto()));
        voto.setIdAssociado(votoRequest.getIdAssociado());
        voto.setPauta(pauta);

        return voto;
    }
}
