package br.com.softdesign.votacao.enums;

import br.com.softdesign.votacao.exceptions.VotoInvalidoException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum OpcaoVoto {

    SIM(1, "Sim"), NAO(2, "Não");

    private int id;
    private String descricao;

    public static OpcaoVoto fromString(String value) throws VotoInvalidoException {

        for (OpcaoVoto opcaoVoto : OpcaoVoto.values()) {
            if (opcaoVoto.descricao.equalsIgnoreCase(value)) {
                return opcaoVoto;
            }
        }

        throw new VotoInvalidoException("Valores de votos permitido: Sim/Não.");
    }
}