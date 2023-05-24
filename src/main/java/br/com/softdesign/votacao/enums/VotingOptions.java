package br.com.softdesign.votacao.enums;

import br.com.softdesign.votacao.exceptions.InvalidVoteException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum VotingOptions {

    SIM(1, "Sim"), NAO(2, "Não");

    private int id;
    private String description;

    public static VotingOptions fromString(String value) throws InvalidVoteException {

        for (VotingOptions votingOptions : VotingOptions.values()) {
            if (votingOptions.description.equalsIgnoreCase(value)) {
                return votingOptions;
            }
        }

        throw new InvalidVoteException("Valores de voto permitido: Sim/Não.");
    }
}