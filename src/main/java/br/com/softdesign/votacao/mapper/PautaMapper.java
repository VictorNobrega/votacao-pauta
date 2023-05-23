package br.com.softdesign.votacao.mapper;

import br.com.softdesign.votacao.dto.PautaRequest;
import br.com.softdesign.votacao.dto.PautaResponse;
import br.com.softdesign.votacao.dto.ResultadoPautaResponse;
import br.com.softdesign.votacao.enums.OpcaoVoto;
import br.com.softdesign.votacao.model.Pauta;
import br.com.softdesign.votacao.model.Voto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class PautaMapper {

    public static Pauta mapper(PautaRequest pautaRequest) {

        Pauta pauta = new Pauta();
        pauta.setDescricao(pautaRequest.getDescricao());

        return pauta;
    }

    public static ResultadoPautaResponse mapper(Pauta pauta) {

        long quantidadeVotosSim = getQuantidadeVotosSim(pauta);
        long quantidadeVotosNao = getQuantidadeVotosNao(pauta.getVotos(), quantidadeVotosSim);

        return ResultadoPautaResponse.builder()
                .id(pauta.getId())
                .descricao(pauta.getDescricao())
                .resultado(getResultado(quantidadeVotosSim, quantidadeVotosNao))
                .votosNao(quantidadeVotosNao)
                .votosSim(quantidadeVotosSim)
                .build();
    }

    private static String getResultado(long quantidadeVotosSim, long quantidadeVotosNao) {

        String resultado;

        if (quantidadeVotosSim == quantidadeVotosNao) {
            resultado = "Empate";
        } else {
            resultado = quantidadeVotosSim > quantidadeVotosNao ? "Sim" : "Não";
        }

        return resultado;
    }

    private static long getQuantidadeVotosNao(List<Voto> votos, long quantidadeVotosSim) {
        return votos.size() - quantidadeVotosSim;
    }

    private static long getQuantidadeVotosSim(Pauta pauta) {
        return pauta.getVotos().stream()
                .filter(voto -> OpcaoVoto.SIM.equals(voto.getOpcaoVoto()))
                .count();
    }

    public static List<PautaResponse> mapper(List<Pauta> pautas) {
        return pautas.stream()
                .map(pauta -> convertPauta(pauta))
                .collect(Collectors.toList());

    }

    private static PautaResponse convertPauta(Pauta pauta) {
        return PautaResponse.builder()
                .id(pauta.getId())
                .descricao(pauta.getDescricao())
                .aptaParaSessao(Objects.isNull(pauta.getSessao()))
                .build();
    }
}
